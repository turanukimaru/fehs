package jp.blogspot.turanukimaru.fehbs

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import jp.blogspot.turanukimaru.fehs.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.onClick
import java.util.Locale


/**
 * 戦闘シミュ画面。Appから呼ばれる。
 */

class BattleSimulatorActivity : AppCompatActivity() {

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
        Log.i("BattleSimulatorActivity", "onCreate")

        setContentView(R.layout.activity_main)
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
        //スピナー作成
        val spinnerWeapon = findViewById<Spinner>(R.id.spinner_weapon)
        val spinnerMove = findViewById<Spinner>(R.id.spinner_move)
        //SAM使えない…
        //onItemClick はspinnerでは使えない（けど継承してしまってる）のでsetOnItemClickListener cannot be used with a spinnerエラーになる。継承の弊害か。spinnerWeapon.onItemClick(spinnerListener)

        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            //作成時にクロージャへのアクセスがあるので初期化してないデータを見てないか注意すること
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val weaponType = WeaponType.weaponTypeOf(spinnerWeapon.selectedItem.toString())
                val moveType = MoveType.moveTypeOf(spinnerMove.selectedItem.toString())
                val units = ArmedHeroRepository.allItems(true)
                val texts = units.filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> e.localeName(locale) }.map { e -> e.localeName(locale) }

                createRadioButton(contentView!!, R.id.attackerRadioButton, R.string.unit_name_title, texts)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //特に何もしない
            }
        }
        val statusView =  findViewById<TextView>(R.id.statusView)
        val unitListener = object : AdapterView.OnItemSelectedListener {
            //作成時にクロージャへのアクセスがあるので初期化してないデータを見てないか注意すること
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val armedClass = ArmedHeroRepository.getById(findViewById<Button>(R.id.attackerRadioButton)!!.text.toString())
                        ?: return//無いときはそのまま戻れるみたい。凄い！
                Log.i("BattleSimulatorActivity", "armedHero : $armedClass")
                val battleUnit = buildBattleUnit(armedClass)
               statusView?.text = battleUnit.armedHero.statusSkillText(locale)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //特に何もしない
            }
        }
        spinnerWeapon.onItemSelectedListener = spinnerListener
        spinnerMove.onItemSelectedListener = spinnerListener

        //なぜかかっこつけないとコンパイルエラーに。はて？
        (findViewById<Spinner>(R.id.boonSpinner)).onItemSelectedListener=unitListener

        (findViewById<Spinner>(R.id.baneSpinner)).onItemSelectedListener=unitListener

        (findViewById<Spinner>(R.id.levelBoostSpinner)).onItemSelectedListener=unitListener

        //計算実行ボタン作成
        findViewById<Button>(R.id.button).onClick { _ ->
            Log.i("BattleSimulatorActivity", "fightStartButton onClick")
            val armedClass = ArmedHeroRepository.getById(findViewById<Button>(R.id.attackerRadioButton)!!.text.toString())
                    ?: return@onClick//無いときはそのまま戻れるみたい。凄い！
            Log.i("BattleSimulatorActivity", "armedHero : $armedClass")
            val battleUnit = buildBattleUnit(armedClass)
            battleUnit.defensiveTerrain = findViewById<CheckBox>(R.id.defTerrainCheckBox).isChecked || battleUnit.defensiveTerrain
            //TODO:編集画面にも隣接ユニット数を追加すること
            battleUnit.adjacentUnits = if (findViewById<CheckBox>(R.id.adjacentCheckBox).isChecked) 1 else 0
//            battleUnit.spdBuff =  findSpinnerValOrNull(R.id.spdBuffSpinner) ?: armedClass.spdBuff
            battleUnit.atkBuff = findSpinnerValOrNull(R.id.atkBuffSpinner) ?: armedClass.atkBuff
            battleUnit.spdBuff = findSpinnerValOrNull(R.id.spdBuffSpinner) ?: armedClass.spdBuff
            battleUnit.defBuff = findSpinnerValOrNull(R.id.defBuffSpinner) ?: armedClass.defBuff
            battleUnit.resBuff = findSpinnerValOrNull(R.id.resBuffSpinner) ?: armedClass.resBuff
            battleUnit.atkEffect = findSpinnerValOrNull(R.id.atkSpurSpinner) ?: armedClass.atkSpur
            battleUnit.spdEffect = findSpinnerValOrNull(R.id.spdSpurSpinner) ?: armedClass.spdSpur
            battleUnit.defEffect = findSpinnerValOrNull(R.id.defSpurSpinner) ?: armedClass.defSpur
            battleUnit.resEffect = findSpinnerValOrNull(R.id.resSpurSpinner) ?: armedClass.resSpur
            battleUnit.specialCount = findSpinnerValOrNull(R.id.specialChargeSpinner) ?: 0
            battleUnit.hp = battleUnit.hp * (findSpinnerValOrNull(R.id.damageTakenSpinner) ?: 100) / 100
            val spinnerEnemyWeapon = findViewById<Spinner>(R.id.spinner_enemy_weapon)
            val spinnerEnemyMove = findViewById<Spinner>(R.id.spinner_enemy_move)
            val weaponType = WeaponType.weaponTypeOf(spinnerEnemyWeapon.selectedItem.toString())
            Log.i("BattleSimulatorActivity", "weaponType : $weaponType")
            val moveType = MoveType.moveTypeOf(spinnerEnemyMove.selectedItem.toString())
            Log.i("BattleSimulatorActivity", "moveType : $moveType")
            val includeDB = findViewById<CheckBox>(R.id.includeDbCheckBox)!!.isChecked
            val switch = findViewById<CheckBox>(R.id.switchCheckBox)!!.isChecked
            val filteredUnits = ArmedHeroRepository.allItems(includeDB).filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> (if (e.baseHero.color == 0) 4 else e.baseHero.color).toString() + e.baseHero.weaponType.sortOrder.toString() + e.localeName(locale) }
            val atkBuff = findSpinnerValOrNull(R.id.atkTargetBuffSpinner)
            val spdBuff = findSpinnerValOrNull(R.id.spdTargetBuffSpinner)
            val defBuff = findSpinnerValOrNull(R.id.defTargetBuffSpinner)
            val resBuff = findSpinnerValOrNull(R.id.resTargetBuffSpinner)
            val atkSpur = findSpinnerValOrNull(R.id.atkTargetSpurSpinner)
            val spdSpur = findSpinnerValOrNull(R.id.spdTargetSpurSpinner)
            val defSpur = findSpinnerValOrNull(R.id.defTargetSpurSpinner)
            val resSpur = findSpinnerValOrNull(R.id.resTargetSpurSpinner)
            val defensiveTargetTerrain = findViewById<CheckBox>(R.id.defTerrainTargetCheckBox).isChecked
            val levelBoost10 = findViewById<CheckBox>(R.id.merge10TargetCheckBox).isChecked
            fun BattleUnit.buff(): BattleUnit {
                this.atkBuff = atkBuff ?: this.atkBuff
                this.spdBuff = spdBuff ?: this.spdBuff
                this.defBuff = defBuff ?: this.defBuff
                this.resBuff = resBuff ?: this.resBuff
                this.atkEffect = atkSpur ?: this.atkEffect
                this.spdEffect = spdSpur ?: this.spdEffect
                this.defEffect = defSpur ?: this.defEffect
                this.resEffect = resSpur ?: this.resEffect
                this.defensiveTerrain = defensiveTargetTerrain || this.defensiveTerrain
                this.armedHero.levelBoost = if (levelBoost10) 10 else this.armedHero.levelBoost
                this.armedHero.equip()
                return this
            }
            Log.i("BattleSimulatorActivity", "targetList : $filteredUnits")
//            val resultList = filteredUnits.fold(mutableListOf<List<AttackResult>>()) { list, e -> list.add(if (switch) BattleUnit(e, e.maxHp).fight(battleUnit) else battleUnit.fight(BattleUnit(e, e.maxHp)));list }
            val resultList = filteredUnits.map({ e -> if (switch) BattleUnit(e, e.maxHp).buff().fightAndAfterEffect(battleUnit) else battleUnit.fightAndAfterEffect(BattleUnit(e, e.maxHp).buff()) })
            Log.i("BattleSimulatorActivity", "resultList : $resultList")

            //計算結果
            recyclerView.adapter = SimpleItemRecyclerViewAdapter(resultList, switch)
        }
        //クリアボタン作成。全部の要素を一気にクリアする方法ないかなあ。
        findViewById<Button>(R.id.reset_button).onClick { _ ->
            findViewById<Spinner>(R.id.boonSpinner).setSelection(0)
            findViewById<Spinner>(R.id.baneSpinner).setSelection(0)
            findViewById<Spinner>(R.id.atkBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.spdBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.defBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.resBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.atkSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.spdSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.defSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.resSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.levelBoostSpinner).setSelection(0)
            findViewById<Spinner>(R.id.specialChargeSpinner).setSelection(0)
            findViewById<Spinner>(R.id.damageTakenSpinner).setSelection(0)
            findViewById<Spinner>(R.id.atkTargetBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.spdTargetBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.defTargetBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.resTargetBuffSpinner).setSelection(0)
            findViewById<Spinner>(R.id.atkTargetSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.spdTargetSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.defTargetSpurSpinner).setSelection(0)
            findViewById<Spinner>(R.id.resTargetSpurSpinner).setSelection(0)

            findViewById<CheckBox>(R.id.defTerrainCheckBox).isChecked = false
            findViewById<CheckBox>(R.id.adjacentCheckBox).isChecked = false
            findViewById<CheckBox>(R.id.merge10TargetCheckBox).isChecked = false
            findViewById<CheckBox>(R.id.defTerrainTargetCheckBox).isChecked = false
            findViewById<CheckBox>(R.id.switchCheckBox).isChecked = false
        }
        //登録ボタン作成。ここはほぼテンプレートのまま
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (mTwoPane) {
                val arguments = Bundle()
                arguments.putString(HeroRegisterFragment.ARG_ITEM_ID, "")

                val fragment = HeroRegisterFragment()
                fragment.arguments = arguments
                supportFragmentManager.beginTransaction()//FragmentActivity.getSupportFragmentManager
                        .replace(R.id.sake_detail_container, fragment)
                        .commit()
            } else {
                val context = view.context
                val intent = Intent(context, HeroRegisterActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    private fun buildBattleUnit(armedClass: ArmedHero): BattleUnit {
        val battleUnit = BattleUnit(armedClass, armedClass.maxHp)
        val boon = BoonType.boonTypeOf(findViewById<Spinner>(R.id.boonSpinner).selectedItem.toString())
        val bane = BoonType.boonTypeOf(findViewById<Spinner>(R.id.baneSpinner).selectedItem.toString())
        battleUnit.armedHero.boon = if (boon != BoonType.NONE) boon else battleUnit.armedHero.boon
        battleUnit.armedHero.bane = if (bane != BoonType.NONE) bane else battleUnit.armedHero.bane
        battleUnit.armedHero.levelBoost = findSpinnerValOrNull(R.id.levelBoostSpinner) ?: armedClass.levelBoost
        println( findViewById<Spinner>(R.id.levelBoostSpinner))
        println( findViewById<Spinner>(R.id.levelBoostSpinner)?.selectedItem)
        println( findViewById<Spinner>(R.id.levelBoostSpinner)?.selectedItem?.toString())
        battleUnit.armedHero.equip()
        return battleUnit
    }

    private fun findSpinnerValOrNull(spinner: Int): Int? = findViewById<Spinner>(spinner)?.selectedItem?.toString()?.toIntOrNull()

    /**
     * ラジオボタン作成。
     */
    fun createRadioButton(rootView: View, radioButton: Int, title: Int, texts: List<String>) {
        //項目選択.
        val button = rootView.findViewById<RadioButton>(radioButton)
        button.text = texts.first()
        rootView.findViewById<TextView>(R.id.statusView).text = ArmedHeroRepository.getById(texts.first())?.statusSkillText(locale)

        button.setOnClickListener { v ->
            val index = texts.indexOf(rootView.findViewById<RadioButton>(radioButton).text)
            val builder = AlertDialog.Builder(v.context)
            builder.setTitle(title).setCancelable(true).setSingleChoiceItems(texts.toTypedArray(), if (index >= 0) index else 0
                    , { dialog, which ->
                rootView.findViewById<RadioButton>(radioButton).text = texts[which]
                val selectedUnit = buildBattleUnit(ArmedHeroRepository.getById(texts[which])!!)
                rootView.findViewById<TextView>(R.id.statusView).text = selectedUnit.armedHero.statusSkillText(locale)

                dialog.dismiss()
            })
            val dialog = builder.create()
            dialog.show()
        }

    }

    /**
     * メニュー作成時。
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * メニュー項目を推したときの処理
     */
//    override fun onOptionsItemSelected(item: MenuItem): Boolean = super.onOptionsItemSelected(item)

    /**
     * リスト作ってるところ。ここでRealmから取り出せばいいか
     */
    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<List<AttackResult>>, private val switch: Boolean) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fight_result_list_content, parent, false)
            return ViewHolder(view)
        }

        //bind時実行
        //2回呼ばれることがある・・・
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mItem = mValues[position]
            val source = mItem.last().source
            val target = mItem.last().target
            holder.mItem = mItem
            //戦闘結果は攻撃側をsourceとしているので攻守切り替え時には逆にする必要がある
            if (switch) {
                holder.sourceHp.text = target.hp.toString()
                holder.targetHp.text = source.hp.toString()
//                holder.mSpecView.text = source.armedHero.localeName(locale)
                holder.unitText.text = source.armedHero.localeName(locale)
                holder.mView.findViewById<TextView>(R.id.unitText).setTextColor(when {
                    target.hp == 0 -> Color.RED
                    source.hp == 0 -> Color.BLUE
                    else -> Color.BLACK
                })
                holder.statusText.text = source.armedHero.statusText
            } else {
                holder.sourceHp.text = source.hp.toString()
                holder.targetHp.text = target.hp.toString()
//                holder.mSpecView.text = target.armedHero.localeName(locale)
                holder.unitText.text = target.armedHero.localeName(locale)
                holder.mView.findViewById<TextView>(R.id.unitText).setTextColor(when {
                    source.hp == 0 -> Color.RED
                    target.hp == 0 -> Color.BLUE
                    else -> Color.BLACK
                })
                holder.statusText.text = target.armedHero.statusText
            }
            //結果表示用の領域をもう1行用意してあるんだけど要らないかなあ
//            holder.progressText.text = mItem.detail
//            holder.unitText.text = mItem.date

            holder.progressText.text = mItem.fold("", { string, item -> string + " " + item.detailsShort(if (switch) SIDES.COUNTER else SIDES.ATTACKER, locale) })
            //オンクリック時の動作。TODO:スキル効果を全部書きたいところだが
//            holder.mView.setOnClickListener { _ ->
            //                if (mTwoPane) {
//                    val arguments = Bundle()
//                    arguments.putString(ArmedClassRegisterFragment.ARG_ITEM_ID, holder.mItem!!.id.toString())//!!はnullの時に例外
//                    val fragment = ArmedClassRegisterFragment()
//                    fragment.arguments = arguments
//                    supportFragmentManager.beginTransaction()//FragmentActivity.getSupportFragmentManager
//                            .replace(R.id.sake_detail_container, fragment)
//                            .commit()
//                } else {
//                toast(mItem.fold(
//                        "", { string, item -> string + "\r\n" + item.details() }
//                ))
//            }
        }

        override fun getItemCount(): Int = mValues.size

        /**
         * リスト内のviewを管理する
         */
        inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            //("Can be joined with assignment") が出たらこう書ける。元はinitで初期化していた。
            var mItem: List<AttackResult>? = null
            val sourceHp: TextView = mView.findViewById(R.id.sourceHp)
            val targetHp: TextView = mView.findViewById(R.id.targetHp)
            val unitText: TextView = mView.findViewById(R.id.unitText)
            val statusText: TextView = mView.findViewById(R.id.statusText)
            //            val mSpecView: TextView = mView.findViewById(R.id.itemSpec)
            //途中経過や顔を表示しようとしていた領域
//            val mImageView: ImageView = mView.findViewById(R.id.itemIimageView)
            val progressText: TextView = mView.findViewById(R.id.progressText)

            override fun toString(): String = mItem!!.last().side.name
        }
    }
}