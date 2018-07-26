package jp.blogspot.turanukimaru.fehbs

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.*
import jp.blogspot.turanukimaru.fehs.*
import kotlinx.android.synthetic.main.activity_heroes.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.util.Locale

import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle

/**
 * 登録したユニット一覧
 *
 */

class RegisteredHeroesActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

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
        //ぬるぽでおちる。画面との整合性が合わないか
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val drawer=findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle=ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView=findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


    }

    private val locale = LocaleAdapter(Locale.getDefault()).locale

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

        //ひょっとしてコンテキストがおかしかったのかなあ。でもコンテキストがおかしかったら落ちてたよな今まで
//        findViewById<Button>(R.id.float_button).setOnClickListener { view ->
//            view.   context.startService(Intent(view.context, HeroStatusService::class.java))
//        }
//        findViewById<Button>(R.id.close_button).setOnClickListener { view ->
//            view.context.stopService(Intent(view.context, HeroStatusService::class.java))
//        }

    }

    /**
     * メニュー初期化
     */
     fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu)
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.detail, menu)
    }

    /**
     * リスト作ってるところ
     */
    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<ArmedHero>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.heroes_list_content, parent, false)
            val holder = ViewHolder(view)
            view.onClick { view-> toast(""+holder.adapterPosition)}
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
        }

        override fun getItemCount(): Int = mValues.size

        /**
         * リスト内のviewを管理する
         */
        inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            //("Can be joined with assignment") が出たらこう書ける。元はinitで初期化していた。
            var mItem: ArmedHero? = null
            val name: TextView = mView.findViewById(R.id.unitText)
            val rarity: TextView = mView.findViewById(R.id.rarityText)
            val boost: TextView = mView.findViewById(R.id.boostText)
            val score: TextView = mView.findViewById(R.id.scoreText)
            val status: TextView = mView.findViewById(R.id.statusText)
            //途中経過や顔を表示しようとしていた領域
//            val mImageView: ImageView = mView.findViewById(R.id.itemIimageView)
            val skillText: TextView = mView.findViewById(R.id.skillText)
        }
    }

    /**
     *
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_open_calc -> {
                applicationContext.startService(Intent(applicationContext, HeroStatusService::class.java))
            }
            R.id.nav_close_calc -> {
                applicationContext.stopService(Intent(applicationContext, HeroStatusService::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

}