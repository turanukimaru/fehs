package jp.blogspot.turanukimaru.fehbs

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.*
import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.*
import jp.blogspot.turanukimaru.repos.BattleClassContent
import org.jetbrains.anko.contentView
import java.util.*


/**
 * A fragment representing a single Sake detail screen.
 * This fragment is either contained in a
 * in two-pane mode (on tablets) or a [ArmedClassRegisterActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class BattleClassRegisterFragment : Fragment() {

    val locale = Locale.getDefault()

    /**
     * 画面初期化
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.unit_register_body, container, false)

        setHasOptionsMenu(true)
//        createSkillRadioButton(rootView, R.id.rarityRadioButton, R.string.rarities_title, R.array.rarities)
//        createSkillRadioButton(rootView, R.id.levelBoostRadioButton, R.string.levelBoost_title, R.array.levelBoost)
        createSkillRadioButton(rootView, R.id.boonRadioButton, R.string.boon_title, R.array.boon_type)
        createSkillRadioButton(rootView, R.id.baneRadioButton, R.string.bane_title, R.array.boon_type)
        createSkillRadioButton(rootView, R.id.weaponRadioButton, R.string.weapon_title, R.array.weapons_swords)
        createSkillRadioButton(rootView, R.id.assistRadioButton, R.string.assist_title, R.array.assists)
        createSkillRadioButton(rootView, R.id.specialRadioButton, R.string.special_title, R.array.specials)
        createSkillRadioButton(rootView, R.id.aSkillRadioButton, R.string.aSkill_title, R.array.aSkills)
        createSkillRadioButton(rootView, R.id.bSkillRadioButton, R.string.bSkill_title, R.array.bSkills)
        createSkillRadioButton(rootView, R.id.cSkillRadioButton, R.string.cSkill_title, R.array.cSkills)
        createSkillRadioButton(rootView, R.id.sealRadioButton, R.string.seal_title, R.array.seals)

        val buffSpinnerListener = object : AdapterView.OnItemSelectedListener {
            //何もしない
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}

            //何もしない
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        rootView.findViewById<Spinner>(R.id.atkBuffSpinner).onItemSelectedListener = buffSpinnerListener

        val spinnerWeapon = rootView.findViewById<Spinner>(R.id.spinner_weapon3)
        val spinnerMove = rootView.findViewById<Spinner>(R.id.spinner_move3)
        //SAM使えない…
        //onItemClick はspinnerでは使えない（けど継承してしまってる）のでsetOnItemClickListener cannot be used with a spinnerエラーになる
//        spinnerWeapon.onItemClick(spinnerListener)
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            //作成時にもクロージャは存在確認されるのでNullPointerExceptionで落ちないように気を付ける必要がある
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = createUnitRadioButton(rootView, R.id.baseUnitRadioButton, R.string.unit_name_title)


            override fun onNothingSelected(parent: AdapterView<*>?) {
//何もしない
            }
        }
        spinnerWeapon.onItemSelectedListener = spinnerListener
        spinnerMove.onItemSelectedListener = spinnerListener

        val boostsSpinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = equip(rootView)
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        rootView.findViewById<Spinner>(R.id.raritySpinner).onItemSelectedListener = boostsSpinnerListener
        rootView.findViewById<Spinner>(R.id.levelBoostSpinner).onItemSelectedListener = boostsSpinnerListener

        //保存ボタンの動作。長いときは別にしたほうが良いけどこっちの描き方だとクロージャが使えるんだよな
        rootView.findViewById<Button>(R.id.ok_button).setOnClickListener { _ ->
            //デフォルトの名前をそのまま使ったときはエラーを吐いてもどる
            if (BattleUnitRepository.isStandardBattleClass(rootView.findViewById<TextView>(R.id.unitName).text.toString())) {
                Toast.makeText(rootView.context, R.string.alert_default_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val baseClass = BattleUnitRepository.getById(rootView.findViewById<Button>(R.id.baseUnitRadioButton).text.toString()) ?: return@setOnClickListener
            val armedClass = equipment(rootView, baseClass.battleClass)
            BattleUnitRepository.createItem(armedClass)
            Toast.makeText(rootView.context, R.string.alert_create_unit, Toast.LENGTH_SHORT).show()
            createUnitRadioButton(rootView, R.id.baseUnitRadioButton, R.string.unit_name_title)
        }
        return rootView
    }

    private fun equipment(rootView: View, baseClass: BattleClass): ArmedClass {
        return ArmedClass(
                baseClass,
                rootView.findViewById<TextView>(R.id.unitName).text.toString()
                , Weapon.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text.toString())
                , Assist.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.assistRadioButton).text.toString())
                , Special.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.specialRadioButton).text.toString())
                , SkillC.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text.toString())
                , SkillC.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text.toString())
                , SkillC.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text.toString())
                , Seal.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.sealRadioButton).text.toString())

                , rootView.findViewById<Spinner>(R.id.raritySpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.levelBoostSpinner).selectedItem.toString().toInt()
                , BoonType.boonTypeOf(rootView.findViewById<RadioButton>(R.id.boonRadioButton).text.toString())
                , BoonType.boonTypeOf(rootView.findViewById<RadioButton>(R.id.baneRadioButton).text.toString())
                , rootView.findViewById<CheckBox>(R.id.defTerrainCheckBox).isChecked
                , rootView.findViewById<Spinner>(R.id.atkBuffSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.spdBuffSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.defBuffSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.resBuffSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.atkSpurSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.spdSpurSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.defSpurSpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.resSpurSpinner).selectedItem.toString().toInt()
        )
    }

    /**
     * ラジオボタン作成。スキルとかXML定義の物
     */
    fun createSkillRadioButton(rootView: View, radioButton: Int, title: Int, items: Int) {
        createSkillRadioButton(rootView, radioButton, title, resources.getStringArray(items))
    }

    fun createSkillRadioButton(rootView: View, radioButton: Int, title: Int, strings: Array<String>) {
        //項目選択.
        rootView.findViewById<RadioButton>(radioButton).setOnClickListener { v ->
            val index = strings.indexOf(rootView.findViewById<RadioButton>(radioButton).text)
            val builder = AlertDialog.Builder(v.context).setTitle(title).setCancelable(true)
            //if式があれば三項演算子が不要なのはわかるが読みやすくはないな…
            builder.setSingleChoiceItems(strings, if (index >= 0) index else 0
                    // 選択したときは文字列をそのまま置き換える
                    , { dialog, which ->
                rootView.findViewById<RadioButton>(radioButton).text = strings[which]
                equip(rootView)
                dialog.dismiss()
            })
            val dialog = builder.create()
            dialog.show()
        }
    }

    fun equip(view: View) {
        val baseClass = BattleUnitRepository.getById(view.findViewById<Button>(R.id.baseUnitRadioButton)?.text.toString()) ?: return
        val armedClass = equipment(view, baseClass.battleClass)
        showParams(view, armedClass)
    }

    private fun showParams(rootView: View, it: ArmedClass) {
//        rootView.findViewById<TextView>(R.id.statusView2).text = it.baseStatusText
//        rootView.findViewById<TextView>(R.id.statusView1).text = it.statusText
        val goodStatus = it.goodStatus()
        rootView.findViewById<TextView>(R.id.hpBaseTitleView).setTextColor(goodBadToColor(goodStatus.hp))
        rootView.findViewById<TextView>(R.id.atkBaseTitleView).setTextColor(goodBadToColor(goodStatus.atk))
        rootView.findViewById<TextView>(R.id.spdBaseTitleView).setTextColor(goodBadToColor(goodStatus.spd))
        rootView.findViewById<TextView>(R.id.defBaseTitleView).setTextColor(goodBadToColor(goodStatus.def))
        rootView.findViewById<TextView>(R.id.resBaseTitleView).setTextColor(goodBadToColor(goodStatus.res))
        rootView.findViewById<TextView>(R.id.hpTitleView).setTextColor(goodBadToColor(goodStatus.hp))
        rootView.findViewById<TextView>(R.id.atkTitleView).setTextColor(goodBadToColor(goodStatus.atk))
        rootView.findViewById<TextView>(R.id.spdTitleView).setTextColor(goodBadToColor(goodStatus.spd))
        rootView.findViewById<TextView>(R.id.defTitleView).setTextColor(goodBadToColor(goodStatus.def))
        rootView.findViewById<TextView>(R.id.resTitleView).setTextColor(goodBadToColor(goodStatus.res))
        val lv1Status = it.lv1equip()
        rootView.findViewById<TextView>(R.id.hpBaseView).text = lv1Status.hp.toString()
        rootView.findViewById<TextView>(R.id.atkBaseView).text = lv1Status.atk.toString()
        rootView.findViewById<TextView>(R.id.spdBaseView).text = lv1Status.spd.toString()
        rootView.findViewById<TextView>(R.id.defBaseView).text = lv1Status.def.toString()
        rootView.findViewById<TextView>(R.id.resBaseView).text = lv1Status.res.toString()
        rootView.findViewById<TextView>(R.id.hpBaseView).setTextColor(goodBadToColor(it.boonHp))
        rootView.findViewById<TextView>(R.id.atkBaseView).setTextColor(goodBadToColor(it.boonAtk))
        rootView.findViewById<TextView>(R.id.spdBaseView).setTextColor(goodBadToColor(it.boonSpd))
        rootView.findViewById<TextView>(R.id.defBaseView).setTextColor(goodBadToColor(it.boonDef))
        rootView.findViewById<TextView>(R.id.resBaseView).setTextColor(goodBadToColor(it.boonRes))
        it.equip()
        rootView.findViewById<TextView>(R.id.hpView).text = it.maxHp.toString()
        rootView.findViewById<TextView>(R.id.atkView).text = it.atk.toString()
        rootView.findViewById<TextView>(R.id.spdView).text = it.spd.toString()
        rootView.findViewById<TextView>(R.id.defView).text = it.def.toString()
        rootView.findViewById<TextView>(R.id.resView).text = it.res.toString()
        rootView.findViewById<TextView>(R.id.hpView).setTextColor(goodBadToColor(it.boonHp))
        rootView.findViewById<TextView>(R.id.atkView).setTextColor(goodBadToColor(it.boonAtk))
        rootView.findViewById<TextView>(R.id.spdView).setTextColor(goodBadToColor(it.boonSpd))
        rootView.findViewById<TextView>(R.id.defView).setTextColor(goodBadToColor(it.boonDef))
        rootView.findViewById<TextView>(R.id.resView).setTextColor(goodBadToColor(it.boonRes))
    }

    private fun goodBadToColor(i: Int): Int = when (i) {
        -1 -> Color.RED
        1 -> Color.BLUE
        else -> Color.BLACK
    }

    /**
     * ラジオボタン作成。キャラクター名
     */
    fun createUnitRadioButton(rootView: View, radioButton: Int, title: Int) {
        val spinnerWeapon = rootView.findViewById<Spinner>(R.id.spinner_weapon3)
        val spinnerMove = rootView.findViewById<Spinner>(R.id.spinner_move3)
        val weaponType = WeaponType.weaponTypeOf(spinnerWeapon.selectedItem.toString())
        val moveType = MoveType.moveTypeOf(spinnerMove.selectedItem.toString())
        val texts = BattleUnitRepository.allItems(true).filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> e.localeName(locale) }.map { e -> e.localeName(locale) }.toTypedArray()


        if (texts.isEmpty()) {
            return
        }
        val button = rootView.findViewById<RadioButton>(radioButton)
        if (!texts.contains(button.text.toString())) {
            button.text = "-"
            rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.assistRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.specialRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.sealRadioButton).text = "-"

            rootView.findViewById<Spinner>(R.id.raritySpinner).setSelection(4)
            rootView.findViewById<Spinner>(R.id.levelBoostSpinner).setSelection(0)
            rootView.findViewById<RadioButton>(R.id.boonRadioButton).text = "-"
            rootView.findViewById<RadioButton>(R.id.baneRadioButton).text = "-"
            rootView.findViewById<CheckBox>(R.id.defTerrainCheckBox).isChecked = false

            rootView.findViewById<Spinner>(R.id.atkBuffSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.spdBuffSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.defBuffSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.resBuffSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.atkSpurSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.spdSpurSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.defSpurSpinner).setSelection(0)
            rootView.findViewById<Spinner>(R.id.resSpurSpinner).setSelection(0)
        }
        button.setOnClickListener { v ->
            val index = texts.indexOf(rootView.findViewById<RadioButton>(radioButton).text)
            val builder = AlertDialog.Builder(v.context).setTitle(title).setCancelable(true)
            //if式があれば三項演算子が不要なのはわかるが読みやすくはないな…indexOFでNull返してくれればエルビス演算子が使えるのに
            builder.setSingleChoiceItems(texts, if (index >= 0) index else 0
                    // 選択したときは文字列をそのまま置き換える
                    , { dialog, which ->
                rootView.findViewById<RadioButton>(radioButton).text = texts[which]
                val armedClass = BattleUnitRepository.getById(rootView.findViewById<Button>(R.id.baseUnitRadioButton).text.toString()) ?: return@setSingleChoiceItems
                armedClass.let {
                    rootView.findViewById<TextView>(R.id.unitName).text = it.localeName(locale)
                    if (it.name.isEmpty()) {
                        rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text = it.weapon.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.assistRadioButton).text = it.assist.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.specialRadioButton).text = it.special.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text = it.aSkill.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text = it.bSkill.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text = it.cSkill.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.sealRadioButton).text = it.seal.localeName(locale)
                    } else {
                        rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text = it.weapon.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.assistRadioButton).text = it.assist.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.specialRadioButton).text = it.special.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text = it.aSkill.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text = it.bSkill.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text = it.cSkill.localeName(locale)
                        rootView.findViewById<RadioButton>(R.id.sealRadioButton).text = it.seal.localeName(locale)
                    }
                    rootView.findViewById<Spinner>(R.id.raritySpinner).setSelection(it.rarity - 1)
                    rootView.findViewById<Spinner>(R.id.levelBoostSpinner).setSelection(it.levelBoost)
                    rootView.findViewById<RadioButton>(R.id.boonRadioButton).text = it.boon.localeName(locale)
                    rootView.findViewById<RadioButton>(R.id.baneRadioButton).text = it.bane.localeName(locale)

                    rootView.findViewById<CheckBox>(R.id.defTerrainCheckBox).isChecked = it.defensiveTerrain
                    rootView.findViewById<Spinner>(R.id.atkBuffSpinner).setSelection(it.atkBuff)
                    rootView.findViewById<Spinner>(R.id.spdBuffSpinner).setSelection(it.spdBuff)
                    rootView.findViewById<Spinner>(R.id.defBuffSpinner).setSelection(it.defBuff)
                    rootView.findViewById<Spinner>(R.id.resBuffSpinner).setSelection(it.resBuff)
                    rootView.findViewById<Spinner>(R.id.atkSpurSpinner).setSelection(it.atkSpur)
                    rootView.findViewById<Spinner>(R.id.spdSpurSpinner).setSelection(it.atkSpur)
                    rootView.findViewById<Spinner>(R.id.defSpurSpinner).setSelection(it.atkSpur)
                    rootView.findViewById<Spinner>(R.id.resSpurSpinner).setSelection(it.atkSpur)

                    showParams(rootView, it)
                    //装備制限はとりあえず後で考える
                    val allSkills = SkillC.spreadItems()
                    createSkillRadioButton(rootView, R.id.weaponRadioButton, R.string.weapon_title, Weapon.spreadItems().filter { e -> e.type == it.battleClass.weaponType.skillType || e == Skill.NONE }.map { e -> e.localeName(locale) }.toTypedArray())
                    createSkillRadioButton(rootView, R.id.assistRadioButton, R.string.assist_title, Assist.spreadItems().map { e -> e.localeName(locale) }.toTypedArray())
                    createSkillRadioButton(rootView, R.id.specialRadioButton, R.string.special_title, Special.spreadItems().map { e -> e.localeName(locale) }.toTypedArray())
                    createSkillRadioButton(rootView, R.id.aSkillRadioButton, R.string.aSkill_title, allSkills.filter { e -> e.type == Skill.SkillType.A || e == Skill.NONE }.map { e -> e.localeName(locale) }.toTypedArray())
                    createSkillRadioButton(rootView, R.id.bSkillRadioButton, R.string.bSkill_title, allSkills.filter { e -> e.type == Skill.SkillType.B || e == Skill.NONE }.map { e -> e.localeName(locale) }.toTypedArray())
                    createSkillRadioButton(rootView, R.id.cSkillRadioButton, R.string.cSkill_title, allSkills.filter { e -> e.type == Skill.SkillType.C || e == Skill.NONE }.map { e -> e.localeName(locale) }.toTypedArray())
                    createSkillRadioButton(rootView, R.id.sealRadioButton, R.string.seal_title, Seal.spreadItems().map { e -> e.localeName(locale) }.toTypedArray())
                }
                dialog.dismiss()
            })
            val dialog = builder.create()
            dialog.show()
        }
    }


    /**
     * メニュー初期化
     */
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.detail, menu)
    }

    /**
     * インテント用定数。もう使うことはないかな？
     */
    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }

    /**
     * メニューとか。今は削除の実
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("ArmedClassRegister", item.itemId.toString())
        val id = item.itemId
        Log.i("ArmedClassRegister", R.id.action_delete.toString())
        //新規の時はなにもしない。削除項目を出さないほうが良いかね
        if (id == R.id.action_delete) {
            Log.i("ArmedClassRegister", "R.id.action_delete GO!")
            //削除ダイアログ作成
            val builder = AlertDialog.Builder(this.context).setMessage(R.string.action_delete).setTitle(R.string.action_delete)
            builder.setPositiveButton(R.string.action_delete, { _, _ ->
                val target = activity.findViewById<TextView>(R.id.unitName)
                if (BattleUnitRepository.isStandardBattleClass(target.text.toString())) {
//                    Toast.makeText(this.context, R.string.alert_default_name, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                BattleClassContent.deleteById(target.text.toString())

                createUnitRadioButton(activity.contentView!!, R.id.baseUnitRadioButton, R.string.unit_name_title)
                target.text = ""
            })
            //何もしないとき何も書かなくていいのかね
            builder.setNegativeButton(R.string.action_cancel, { _, _ -> })
// Create the AlertDialog
            val dialog = builder.create()
            dialog.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
