package jp.blogspot.turanukimaru.fehbs

import android.app.AlertDialog
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.*
import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.*
import org.jetbrains.anko.enabled


class ViewBuilder(private val locale: Locale) {

    fun createRadioButtons(resources: Resources, rootView: View) {
        //        createSkillRadioButton(rootView, R.id.rarityRadioButton, R.string.rarities_title, R.array.rarities)
//        createSkillRadioButton(rootView, R.id.levelBoostRadioButton, R.string.levelBoost_title, R.array.levelBoost)
        createSkillRadioButton(resources, rootView, R.id.weaponRadioButton, R.string.weapon_title, R.array.weapons_swords)
        createSkillRadioButton(resources, rootView, R.id.assistRadioButton, R.string.assist_title, R.array.assists)
        createSkillRadioButton(resources, rootView, R.id.specialRadioButton, R.string.special_title, R.array.specials)
        createSkillRadioButton(resources, rootView, R.id.aSkillRadioButton, R.string.aSkill_title, R.array.aSkills)
        createSkillRadioButton(resources, rootView, R.id.bSkillRadioButton, R.string.bSkill_title, R.array.bSkills)
        createSkillRadioButton(resources, rootView, R.id.cSkillRadioButton, R.string.cSkill_title, R.array.cSkills)
        createSkillRadioButton(resources, rootView, R.id.sealRadioButton, R.string.seal_title, R.array.seals)

    }

    /**
     * ラジオボタン作成。スキルとかXML定義の物
     */
    private fun createSkillRadioButton(resources: Resources, rootView: View, radioButton: Int, title: Int, items: Int) {
        createSkillRadioButton(rootView, radioButton, title, resources.getStringArray(items))
    }

    private fun createSkillRadioButton(rootView: View, radioButton: Int, title: Int, strings: Array<String>) {
        //項目選択.
        rootView.findViewById<RadioButton>(radioButton).setOnClickListener { v ->
            val index = strings.indexOf(rootView.findViewById<RadioButton>(radioButton).text)
            val builder = AlertDialog.Builder(v.context).setTitle(title).setCancelable(true)
            //if式があれば三項演算子が不要なのはわかるが読みやすくはないな…
            builder.setSingleChoiceItems(strings, if (index >= 0) index else 0
                    // 選択したときは文字列をそのまま置き換える
            ) { dialog, which ->
                rootView.findViewById<RadioButton>(radioButton).text = strings[which]
                equip(rootView, rootView.findViewById<Button>(R.id.baseUnitRadioButton)?.text.toString())
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    fun equip(view: View, id: String) {
        val baseClass = ArmedHeroRepository.getById(id)
                ?: return
        val armedClass = equipment(view, baseClass.baseHero)
        showParams(view, armedClass, view.findViewById<Spinner>(R.id.targetRaritySpinner).selectedItem.toString().toInt())
        buildRefines(view, armedClass)
        addWriteEnable(view)
    }

    fun equipStandard(view: View, id: String) {
        val baseClass = ArmedHeroRepository.getById(id)
                ?: return
        val armedHero = ArmedHero(baseClass.baseHero
                , rarity = view.findViewById<Spinner>(R.id.raritySpinner).selectedItem.toString().toInt()
                , levelBoost = view.findViewById<Spinner>(R.id.levelBoostSpinner).selectedItem.toString().toInt()
                , boon = BoonType.boonTypeOf(view.findViewById<Spinner>(R.id.boonRadioButton).selectedItem.toString())
                , bane = BoonType.boonTypeOf(view.findViewById<Spinner>(R.id.baneRadioButton).selectedItem.toString())
        )
        showParams(view, armedHero, view.findViewById<Spinner>(R.id.targetRaritySpinner).selectedItem.toString().toInt())
    }

    fun equipment(rootView: View, baseClass: BaseHero): ArmedHero {
        //    println(rootView.findViewById<RadioButton>(R.id.refineRadioButton).text.toString())
        //    println(RefinedSkill.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.refineRadioButton).text.toString()))
        return ArmedHero(
                baseClass,
                rootView.findViewById<TextView>(R.id.unitName).text.toString()
                , Weapon.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text.toString())
                , RefinedSkill.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.refineRadioButton).text.toString())

                , Assist.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.assistRadioButton).text.toString())
                , Special.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.specialRadioButton).text.toString())
                , SkillA.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text.toString())
                , SkillB.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text.toString())
                , SkillC.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text.toString())
                , Seal.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.sealRadioButton).text.toString())

                , rootView.findViewById<Spinner>(R.id.raritySpinner).selectedItem.toString().toInt()
                , rootView.findViewById<Spinner>(R.id.levelBoostSpinner).selectedItem.toString().toInt()
                , BoonType.boonTypeOf(rootView.findViewById<Spinner>(R.id.boonRadioButton).selectedItem.toString())
                , BoonType.boonTypeOf(rootView.findViewById<Spinner>(R.id.baneRadioButton).selectedItem.toString())
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


    private fun showParams(rootView: View, it: ArmedHero, targetRarity: Int) {
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
        it.rarity = targetRarity
        it.equip()
        rootView.findViewById<TextView>(R.id.hpView).text = it.maxHp.toString()
        rootView.findViewById<TextView>(R.id.atkView).text = it.atk.toString()
        rootView.findViewById<TextView>(R.id.spdView).text = it.spd.toString()
        rootView.findViewById<TextView>(R.id.defView).text = it.def.toString()
        rootView.findViewById<TextView>(R.id.resView).text = it.res.toString()
        rootView.findViewById<TextView>(R.id.scoreText).text = it.score.toString()
        rootView.findViewById<TextView>(R.id.hpView).setTextColor(goodBadToColor(it.boonHp))
        rootView.findViewById<TextView>(R.id.atkView).setTextColor(goodBadToColor(it.boonAtk))
        rootView.findViewById<TextView>(R.id.spdView).setTextColor(goodBadToColor(it.boonSpd))
        rootView.findViewById<TextView>(R.id.defView).setTextColor(goodBadToColor(it.boonDef))
        rootView.findViewById<TextView>(R.id.resView).setTextColor(goodBadToColor(it.boonRes))

    }

    private fun buildRefines(rootView: View, it: ArmedHero) {
        val weapon = Weapon.valueOfOrNONE(rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text.toString())
        val texts = RefinedSkill.spreadItems(weapon).map { e -> e.localeName(locale) }
        if (!texts.contains(it.refinedWeapon.localeName(locale))) {
            rootView.findViewById<RadioButton>(R.id.refineRadioButton).text = ""
        }
        createSkillRadioButton(rootView, R.id.refineRadioButton, R.string.refine_title, texts.toTypedArray())

    }


    fun goodBadToColor(i: Int?): Int = when (i) {
        -1 -> Color.RED
        1 -> Color.BLUE
        else -> Color.BLACK
    }

     fun createEditButtons(resources: Resources, rootView: View, armedClass: ArmedHero) {
        armedClass.let {
            rootView.findViewById<TextView>(R.id.unitName).text = it.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text = it.weapon.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.refineRadioButton).text = ""
            rootView.findViewById<RadioButton>(R.id.refineRadioButton).text = it.refinedWeapon.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.assistRadioButton).text = it.assist.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.specialRadioButton).text = it.special.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text = it.aSkill.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text = it.bSkill.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text = it.cSkill.localeName(locale)
            rootView.findViewById<RadioButton>(R.id.sealRadioButton).text = it.seal.localeName(locale)
            rootView.findViewById<Spinner>(R.id.raritySpinner).setSelection(it.rarity - 1)
            rootView.findViewById<Spinner>(R.id.targetRaritySpinner).setSelection(it.rarity - 1)
            rootView.findViewById<Spinner>(R.id.levelBoostSpinner).setSelection(it.levelBoost)
            rootView.findViewById<Spinner>(R.id.boonRadioButton).setSelection(resources.getStringArray(R.array.boon_type).indexOfFirst { s -> it.boon.localeName(locale) == s })
            rootView.findViewById<Spinner>(R.id.baneRadioButton).setSelection(resources.getStringArray(R.array.boon_type).indexOfFirst { s -> it.bane.localeName(locale) == s })

            rootView.findViewById<CheckBox>(R.id.defTerrainCheckBox).isChecked = it.defensiveTerrain
            rootView.findViewById<Spinner>(R.id.atkBuffSpinner).setSelection(it.atkBuff)
            rootView.findViewById<Spinner>(R.id.spdBuffSpinner).setSelection(it.spdBuff)
            rootView.findViewById<Spinner>(R.id.defBuffSpinner).setSelection(it.defBuff)
            rootView.findViewById<Spinner>(R.id.resBuffSpinner).setSelection(it.resBuff)
            rootView.findViewById<Spinner>(R.id.atkSpurSpinner).setSelection(it.atkSpur)
            rootView.findViewById<Spinner>(R.id.spdSpurSpinner).setSelection(it.spdSpur)
            rootView.findViewById<Spinner>(R.id.defSpurSpinner).setSelection(it.defSpur)
            rootView.findViewById<Spinner>(R.id.resSpurSpinner).setSelection(it.resSpur)

            showParams(rootView, it, it.rarity)
            //装備制限はとりあえず後で考える
            createSkillRadioButton(rootView, R.id.weaponRadioButton, R.string.weapon_title, Weapon.spreadItems(true).filter { e -> e.type.weaponType == it.baseHero.weaponType || e == Skill.NONE }.map { e -> e.localeName(locale) }.toTypedArray())
            createSkillRadioButton(rootView, R.id.refineRadioButton, R.string.refine_title, RefinedSkill.spreadItems(armedClass.baseWeapon).map { e -> e.localeName(locale) }.toTypedArray())

            createSkillRadioButton(rootView, R.id.assistRadioButton, R.string.assist_title, Assist.spreadItems(true).map { e -> e.localeName(locale) }.toTypedArray())
            createSkillRadioButton(rootView, R.id.specialRadioButton, R.string.special_title, Special.spreadItems(true).map { e -> e.localeName(locale) }.toTypedArray())
            createSkillRadioButton(rootView, R.id.aSkillRadioButton, R.string.aSkill_title, SkillA.spreadItems(true).map { e -> e.localeName(locale) }.toTypedArray())
            createSkillRadioButton(rootView, R.id.bSkillRadioButton, R.string.bSkill_title, SkillB.spreadItems(true).map { e -> e.localeName(locale) }.toTypedArray())
            createSkillRadioButton(rootView, R.id.cSkillRadioButton, R.string.cSkill_title, SkillC.spreadItems(true).map { e -> e.localeName(locale) }.toTypedArray())
            createSkillRadioButton(rootView, R.id.sealRadioButton, R.string.seal_title, Seal.spreadItems(true).map { e -> e.localeName(locale) }.toTypedArray())

            addWriteEnable(rootView)
        }

    }

    private fun clearButtons(rootView: View) {
        rootView.findViewById<Spinner>(R.id.raritySpinner).setSelection(4)
        rootView.findViewById<Spinner>(R.id.targetRaritySpinner).setSelection(4)
        rootView.findViewById<Spinner>(R.id.levelBoostSpinner).setSelection(0)
        rootView.findViewById<Spinner>(R.id.boonRadioButton).setSelection(0)
        rootView.findViewById<Spinner>(R.id.baneRadioButton).setSelection(0)

    }

    private fun clearEditButtons(rootView: View) {
        rootView.findViewById<RadioButton>(R.id.weaponRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.refineRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.assistRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.specialRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.aSkillRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.bSkillRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.cSkillRadioButton).text = ""
        rootView.findViewById<RadioButton>(R.id.sealRadioButton).text = ""
//デフォルト5
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

    fun getNameChangeListener(resources: Resources, rootView: View): AdapterView.OnItemSelectedListener? = object : AdapterView.OnItemSelectedListener {
        //作成時にもクロージャは存在確認されるのでNullPointerExceptionで落ちないように気を付ける必要がある
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = createUnitRadioButton(resources, rootView, R.id.baseUnitRadioButton, R.string.unit_name_title)

        override fun onNothingSelected(parent: AdapterView<*>?) {
            //何もしない
        }
    }

    fun boostsSpinnerListener(rootView: View): AdapterView.OnItemSelectedListener? = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = equip(rootView, rootView.findViewById<Button>(R.id.baseUnitRadioButton)?.text.toString())
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    fun getSpinnerChangeListener(rootView: View): AdapterView.OnItemSelectedListener? = object : AdapterView.OnItemSelectedListener {
        //作成時にもクロージャは存在確認されるのでNullPointerExceptionで落ちないように気を付ける必要がある
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = createUnitSpinner(rootView, R.id.baseUnitSpinner)

        override fun onNothingSelected(parent: AdapterView<*>?) {
            //何もしない
        }
    }

    fun boostsListener(rootView: View): AdapterView.OnItemSelectedListener? = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = equipStandard(rootView, rootView.findViewById<Spinner>(R.id.baseUnitSpinner)?.selectedItem.toString())
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    //名前入力イベントを拾えないで旨く動かないことが多い…要らねえかなこれ
    fun addWriteEnable(rootView: View): Boolean {
//        rootView.findViewById<TextView>(R.id.add_button).enabled = false
//        rootView.findViewById<TextView>(R.id.write_button).enabled = false
        val baseName = rootView.findViewById<RadioButton>(R.id.baseUnitRadioButton)?.text.toString()
        val targetName = rootView.findViewById<TextView>(R.id.unitName).text.toString()
        Log.i("addWriteEnable", "$baseName $targetName")
        if (StandardBaseHero.containsKey(targetName)) {
            return true
        }
        //別名がついてるときは追加可能 　TODO:DBみて被ってるときはどっちも不可能のがいいか…
//        rootView.findViewById<TextView>(R.id.add_button).enabled = targetName != baseName
        //ベースがスタンダードでなければ上書きが可能
//        rootView.findViewById<TextView>(R.id.write_button).enabled = !StandardBaseHero.containsKey(baseName)
        return true
    }

    /**
     * ラジオボタン作成。キャラクター名用
     */
    fun createUnitRadioButton(resources: Resources, rootView: View, radioButton: Int, title: Int) {
        val spinnerWeapon = rootView.findViewById<Spinner>(R.id.spinner_weapon3)
        val spinnerMove = rootView.findViewById<Spinner>(R.id.spinner_move3)
        val weaponType = WeaponType.weaponTypeOf(spinnerWeapon.selectedItem.toString())
        val moveType = MoveType.moveTypeOf(spinnerMove.selectedItem.toString())
        val texts = ArmedHeroRepository.allItems(true).filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> e.localeName(locale) }.map { e -> e.localeName(locale) }.toTypedArray()


        if (texts.isEmpty()) {
            return
        }
        val button = rootView.findViewById<RadioButton>(radioButton)
        if (!texts.contains(button.text.toString())) {
            button.text = "-"
            clearButtons(rootView)
            clearEditButtons(rootView)
        }
        button.setOnClickListener { v ->
            //無かった時のための処理
            val index = texts.indexOf(rootView.findViewById<RadioButton>(radioButton).text)
            val builder = AlertDialog.Builder(v.context).setTitle(title).setCancelable(true)
            //setSingleChoiceItemsでアイテムリストとリスナをセットしている
            builder.setSingleChoiceItems(texts, if (index >= 0) index else 0
                    // 選択したときは文字列をそのまま置き換える
            ) { dialog, which ->
                rootView.findViewById<RadioButton>(radioButton).text = texts[which]
                val armedClass = ArmedHeroRepository.getById(rootView.findViewById<Button>(R.id.baseUnitRadioButton).text.toString())
                        ?: return@setSingleChoiceItems
                createEditButtons(resources, rootView, armedClass)
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    /**
     * キャラクター名のスピナー
     */
    fun createUnitSpinner(rootView: View, spinnerId: Int) {
        val spinnerWeapon = rootView.findViewById<Spinner>(R.id.spinner_weapon3)
        val spinnerMove = rootView.findViewById<Spinner>(R.id.spinner_move3)
        val weaponType = WeaponType.weaponTypeOf(spinnerWeapon.selectedItem.toString())
        val moveType = MoveType.moveTypeOf(spinnerMove.selectedItem.toString())
        //adapterに渡すのはCollection...
        val texts = ArmedHeroRepository.allItems(false).filter { e -> e.have(weaponType, moveType) }.sortedBy { e -> e.localeName(locale) }.map { e -> e.localeName(locale) }
        if (texts.isEmpty()) {
            return
        }
        val spinner = rootView.findViewById<Spinner>(spinnerId)
        if (!texts.contains(spinner.selectedItem.toString())) {
            spinner.setSelection(0)
            clearButtons(rootView)
        }

        val adapter = ArrayAdapter<String>(rootView.context, android.R.layout.simple_spinner_item)
        adapter.addAll(texts)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                equipStandard(rootView, rootView.findViewById<Spinner>(R.id.baseUnitSpinner)?.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


}