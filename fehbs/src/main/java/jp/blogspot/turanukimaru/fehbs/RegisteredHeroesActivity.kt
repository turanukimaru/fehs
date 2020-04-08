package jp.blogspot.turanukimaru.fehbs

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import jp.blogspot.turanukimaru.fehs.*
import org.jetbrains.anko.onClick
import java.util.Locale

/**
 * 登録したユニット一覧
 *
 */

class RegisteredHeroesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val locale = LocaleAdapter(Locale.getDefault()).locale
    private val viewBuilder = ViewBuilder(locale)

    companion object {
        const val RELOAD_REQ = 1
    }

    /**
     * 初期化。
     */
    override fun onCreate(savedInstanceState: Bundle?) {//?:nullable
        super.onCreate(savedInstanceState)
        Log.i("RegisteredHeroesAc", "onCreate")

        setContentView(R.layout.activity_heroes)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    /**
     * 画面の要素作成
     */
    override fun onStart() {
        super.onStart()
        val recyclerView = findViewById<RecyclerView>(R.id.fight_result_list)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider, null)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)
        //スピナー作成。スピナーは絞り込み用に残しておこう.ここは一覧なので変更したらすぐ計算しなおしだな
        val spinnerWeapon = findViewById<Spinner>(R.id.spinner_weapon)
        val spinnerMove = findViewById<Spinner>(R.id.spinner_move)

        val registeredHeroes = ArmedHeroRepository.registeredItems()
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            //作成時にクロージャへのアクセスがあるので初期化してないデータを見てないか注意すること
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val weaponType = WeaponType.weaponTypeOf(spinnerWeapon.selectedItem.toString())
                val moveType = MoveType.moveTypeOf(spinnerMove.selectedItem.toString())
                Log.i("BattleSimulatorActivity", "weaponType : $weaponType")
                Log.i("BattleSimulatorActivity", "moveType : $moveType")
                val filteredUnits = registeredHeroes.filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> (if (e.baseHero.color == 0) 4 else e.baseHero.color).toString() + e.baseHero.weaponType.sortOrder.toString() + e.localeName(locale) }
                Log.i("BattleSimulatorActivity", "resultList : $filteredUnits")

                recyclerView.adapter = SimpleItemRecyclerViewAdapter(filteredUnits)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //特に何もしない
            }
        }
        spinnerWeapon.onItemSelectedListener = spinnerListener
        spinnerMove.onItemSelectedListener = spinnerListener

        //登録ボタン作成。ここはほぼテンプレートのまま
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val context = view.context
            val intent = Intent(context, HeroRegisterActivity::class.java)
            context.startActivity(intent)
        }

        //更新要求を受け取るレシーバ
        HeroRepoReceiver.handler = Handler { message ->
            if (message.what == RELOAD_REQ) {
                val weaponType = WeaponType.weaponTypeOf(spinnerWeapon.selectedItem.toString())
                val moveType = MoveType.moveTypeOf(spinnerMove.selectedItem.toString())
                val filteredUnits = ArmedHeroRepository.registeredItems().filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> (if (e.baseHero.color == 0) 4 else e.baseHero.color).toString() + e.baseHero.weaponType.sortOrder.toString() + e.localeName(locale) }
                recyclerView.adapter = SimpleItemRecyclerViewAdapter(filteredUnits)
            }
            true
        }
    }

    /**
     * リスト作ってるところ
     */
    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<ArmedHero>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.heroes_list_content, parent, false)
            val holder = ViewHolder(view)
            view.onClick { v ->
                val context = v?.context
                val intent = Intent(context, HeroRegisterActivity::class.java)
                intent.putExtra(HeroRegisterActivity.HERO_NAME, mValues[holder.adapterPosition].name)
                context?.startActivity(intent)
            }

            return holder
        }

        //bind時実行
        //2回呼ばれることがある・・・
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mItem = mValues[position]
            holder.mItem = mItem
            holder.status.text = mItem.statusText
            holder.name.text = mItem.name
            holder.rarity.text = mItem.rarity.toString()
            holder.boost.text = mItem.levelBoost.toString()
            holder.score.text = mItem.score.toString()
            //結果表示用の領域をもう1行用意してあるんだけど要らないかなあ
            holder.skillText.text = mItem.skillText(locale)

            holder.hpView.text = mItem.maxHp.toString()
            holder.atkView.text = mItem.atk.toString()
            holder.spdView.text = mItem.spd.toString()
            holder.defView.text = mItem.def.toString()
            holder.resView.text = mItem.res.toString()
            holder.score.text = mItem.score.toString()
            holder.hpTitle.setTextColor(viewBuilder.goodBadToColor(mItem.boonHp))
            holder.atkTitle.setTextColor(viewBuilder.goodBadToColor(mItem.boonAtk))
            holder.spdTitle.setTextColor(viewBuilder.goodBadToColor(mItem.boonSpd))
            holder.defTitle.setTextColor(viewBuilder.goodBadToColor(mItem.boonDef))
            holder.resTitle.setTextColor(viewBuilder.goodBadToColor(mItem.boonRes))
        }

        override fun getItemCount(): Int = mValues.size

        /**
         * リスト内のviewを管理する
         */
        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            //("Can be joined with assignment") が出たらこう書ける。元はinitで初期化していた。
            var mItem: ArmedHero? = null
            val name: TextView = mView.findViewById(R.id.unitText)
            val rarity: TextView = mView.findViewById(R.id.rarityText)
            val boost: TextView = mView.findViewById(R.id.boostText)
            val score: TextView = mView.findViewById(R.id.scoreText)
            val status: TextView = mView.findViewById(R.id.statusText)
            val hpTitle: TextView = mView.findViewById(R.id.hpTitleView)
            val hpView: TextView = mView.findViewById(R.id.hpView)
            val atkTitle: TextView = mView.findViewById(R.id.atkTitleView)
            val atkView: TextView = mView.findViewById(R.id.atkView)
            val spdTitle: TextView = mView.findViewById(R.id.spdTitleView)
            val spdView: TextView = mView.findViewById(R.id.spdView)
            val defTitle: TextView = mView.findViewById(R.id.defTitleView)
            val defView: TextView = mView.findViewById(R.id.defView)
            val resTitle: TextView = mView.findViewById(R.id.resTitleView)
            val resView: TextView = mView.findViewById(R.id.resView)

            //途中経過や顔を表示しようとしていた領域
            val skillText: TextView = mView.findViewById(R.id.skillText)
        }
    }

    //    var OVERLAY_PERMISSION_REQ_CODE = 1000 //あれ定数化されてない…？定数どこー？
//
//    /**
//     *　ナビゲーションメニュー。左から出てくるやつね。
//     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//        when (item.itemId) {
//            R.id.nav_open_calc -> {
//                if (!Settings.canDrawOverlays(this)) {
//                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
//                    startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE)
//                } else {
//                    applicationContext.startService(Intent(applicationContext, HeroStatusService::class.java))
//                }
//            }
//            R.id.nav_close_calc -> {
//                applicationContext.stopService(Intent(applicationContext, HeroStatusService::class.java))
//            }
//        }
//        drawer_layout.closeDrawer(GravityCompat.START)
//
        return true
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
//            if (!Settings.canDrawOverlays(this)) {
//                longToast("Permission not granted")
//            } else {
//                applicationContext.startService(Intent(applicationContext, HeroStatusService::class.java))
//            }
//        }
//    }
}