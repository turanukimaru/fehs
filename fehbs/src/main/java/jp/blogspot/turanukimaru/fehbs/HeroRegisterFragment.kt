package jp.blogspot.turanukimaru.fehbs

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import jp.blogspot.turanukimaru.fehs.ArmedHeroRepository
import jp.blogspot.turanukimaru.fehs.R
import jp.blogspot.turanukimaru.repos.RealmArmedHeroContent
import org.jetbrains.anko.contentView


/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class HeroRegisterFragment : Fragment() {

    private val locale = LocaleAdapter(java.util.Locale.getDefault()).locale
    private val viewBuilder = ViewBuilder(locale)

    /**
     * 画面初期化
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.unit_register_body, container, false)

        setHasOptionsMenu(true)

//        viewBuilder.createRadioButtons(resources, rootView)

        val spinnerWeapon = rootView.findViewById<Spinner>(R.id.spinner_weapon3)
        val spinnerMove = rootView.findViewById<Spinner>(R.id.spinner_move3)
        //SAM使えない…
        //onItemClick はspinnerでは使えない（けど継承してしまってる）のでsetOnItemClickListener cannot be used with a spinnerエラーになる
//        spinnerWeapon.onItemClick(spinnerListener)
        val spinnerListener = viewBuilder.getNameChangeListener(resources, rootView)
        spinnerWeapon.onItemSelectedListener = spinnerListener
        spinnerMove.onItemSelectedListener = spinnerListener

        rootView.findViewById<Spinner>(R.id.boonRadioButton).onItemSelectedListener = viewBuilder.boostsSpinnerListener(rootView)
        rootView.findViewById<Spinner>(R.id.baneRadioButton).onItemSelectedListener = viewBuilder.boostsSpinnerListener(rootView)
        rootView.findViewById<Spinner>(R.id.raritySpinner).onItemSelectedListener = viewBuilder.boostsSpinnerListener(rootView)
        rootView.findViewById<Spinner>(R.id.targetRaritySpinner).onItemSelectedListener = viewBuilder.boostsSpinnerListener(rootView)
        rootView.findViewById<Spinner>(R.id.levelBoostSpinner).onItemSelectedListener = viewBuilder.boostsSpinnerListener(rootView)

        //保存ボタンの動作。長いときは別にしたほうが良いけどこっちの描き方だとクロージャが使えるんだよな
        rootView.findViewById<Button>(R.id.add_button).setOnClickListener {
            //デフォルトの名前をそのまま使ったときはエラーを吐いてもどる
            if (ArmedHeroRepository.isStandardBattleClass(rootView.findViewById<TextView>(R.id.unitName).text.toString())) {
                Toast.makeText(rootView.context, R.string.alert_default_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val baseClass = ArmedHeroRepository.getById(rootView.findViewById<Button>(R.id.baseUnitRadioButton).text.toString())
                    ?: return@setOnClickListener
            val armedClass = viewBuilder.equipment(rootView, baseClass.baseHero)
            ArmedHeroRepository.createItem(armedClass)
            Toast.makeText(rootView.context, R.string.alert_create_unit, Toast.LENGTH_SHORT).show()
            viewBuilder.createUnitRadioButton(resources, rootView, R.id.baseUnitRadioButton, R.string.unit_name_title)
        }
        rootView.findViewById<Button>(R.id.write_button).setOnClickListener {
            //デフォルトの名前をそのまま使ったときはエラーを吐いてもどる
            if (ArmedHeroRepository.isStandardBattleClass(rootView.findViewById<TextView>(R.id.unitName).text.toString())) {
                Toast.makeText(rootView.context, R.string.alert_default_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val baseClass = ArmedHeroRepository.getById(rootView.findViewById<Button>(R.id.baseUnitRadioButton).text.toString())
                    ?: return@setOnClickListener
            val armedClass = viewBuilder.equipment(rootView, baseClass.baseHero)
            //自作のユニットを更新したときは元を消す。
            RealmArmedHeroContent.deleteById(baseClass.name)
            ArmedHeroRepository.createItem(armedClass)
            rootView.findViewById<Button>(R.id.baseUnitRadioButton).text = armedClass.name
            Toast.makeText(rootView.context, R.string.alert_update_unit, Toast.LENGTH_SHORT).show()
            viewBuilder.createUnitRadioButton(resources, rootView, R.id.baseUnitRadioButton, R.string.unit_name_title)
        }
        val name = activity?.intent?.getStringExtra(HeroRegisterActivity.HERO_NAME)
        if (name != null) {
            rootView.findViewById<TextView>(R.id.unitName).text = name
            rootView.findViewById<TextView>(R.id.baseUnitRadioButton).text = name
            val armedClass = ArmedHeroRepository.getById(rootView.findViewById<Button>(R.id.baseUnitRadioButton).text.toString())
            if (armedClass != null) {
                viewBuilder.createEditButtons(resources, rootView, armedClass)
            }
        }
        return rootView
    }

    override fun onDestroy() {
        context.stopService(Intent(context, HeroStatusService::class.java))
        super.onDestroy()
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
     * メニューとか。今は削除のみ
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("ArmedClassRegister", item.itemId.toString())
        val id = item.itemId
        Log.i("ArmedClassRegister", R.id.action_delete.toString())
        //新規の時はなにもしない。削除項目を出さないほうが良いかね
        when (id) {
            R.id.action_delete -> {
                Log.i("ArmedClassRegister", "R.id.action_delete GO!")
                //削除ダイアログ作成
                val target = activity.findViewById<TextView>(R.id.unitName)
                val builder = AlertDialog.Builder(this.context).setMessage("Delete : " + target.text.toString()).setTitle(R.string.action_delete)
                builder.setPositiveButton(R.string.action_delete) { _, _ ->
                    if (ArmedHeroRepository.isStandardBattleClass(target.text.toString())) {
                        return@setPositiveButton
                    }
                    RealmArmedHeroContent.deleteById(target.text.toString())

                    viewBuilder.createUnitRadioButton(resources, activity.contentView!!, R.id.baseUnitRadioButton, R.string.unit_name_title)
                    target.text = ""
                }
                //何もしないとき何も書かなくていいのかね
                builder.setNegativeButton(R.string.action_cancel) { _, _ -> }
// Create the AlertDialog
                val dialog = builder.create()
                dialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

