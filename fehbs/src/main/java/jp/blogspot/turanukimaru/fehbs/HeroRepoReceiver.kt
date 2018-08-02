package jp.blogspot.turanukimaru.fehbs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.ArmedHeroRepository
import jp.blogspot.turanukimaru.fehs.BoonType
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import jp.blogspot.turanukimaru.fehs.skill.Skill
import java.text.SimpleDateFormat
import java.util.*

/**
 * サービスから登録・画面の更新を呼ぶためのレシーバ
 */
class HeroRepoReceiver : BroadcastReceiver() {

    companion object {
        var handler: Handler? = null
        const val extraName = "name"
        const val extraBoon = "boon"
        const val extraBane = "bane"
        const val extraRarity = "rarity"
        private const val actionAddHero = "jp.blogspot.turanukimaru.fehbs.ADD_HERO"

        //送信用関数
        fun sendMessage(caller: Context, name: String, boon: String, bane: String, rarity: Int) {
            val intent = Intent()
            intent.action = actionAddHero
            intent.putExtra(extraName, name)
            intent.putExtra(extraBoon, boon)
            intent.putExtra(extraBane, bane)
            intent.putExtra(extraRarity, rarity)
            caller.sendBroadcast(intent)
            Log.i("HeroStatusService", "send intent $name $boon $bane $rarity")
        }
    }

    private val locale = LocaleAdapter(Locale.getDefault()).locale

    //受信用メソッド
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("HeroRepoReceiver", "onReceive")
        if (intent == null) return
        val bundle = intent.extras
        val name = bundle.getString(extraName)
        val boon = BoonType.boonTypeOf(bundle.getString(extraBoon))
        val bane = BoonType.boonTypeOf(bundle.getString(extraBane))
        val rarity = bundle.getInt(extraRarity)

        Log.i("HeroRepoReceiver", "$name $bane $boon $rarity")
        val base = StandardBaseHero.get(name)!!
        val unitName = SimpleDateFormat("yy/MM/dd ", Locale.US).format(Date()) + name + "☆" + rarity + boon.localeName(locale) + "↑" + bane.localeName(locale) + "↓"
        val armedHero = ArmedHero(base
                , unitName
                , base.weapon
                , Skill.NONE
                , base.assist
                , base.special
                , base.aSkill
                , base.bSkill
                , base.cSkill
                , base.seal
                , rarity
                , 0
                , boon
                , bane
                , false
                , 0
                , 0
                , 0
                , 0
                , 0
                , 0
                , 0
                , 0

        )
        armedHero.equip()
        ArmedHeroRepository.createItem(armedHero)
        Toast.makeText(
                context,
                "create unit $unitName ",
                Toast.LENGTH_LONG).show()
        val msg = Message()
        msg.what = RegisteredHeroesActivity.RELOAD_REQ
        //リスト更新要求
        handler?.sendMessage(msg)
    }
}
