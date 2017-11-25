package jp.blogspot.turanukimaru.fehbs

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import jp.blogspot.turanukimaru.fehs.BattleUnitRepository
import jp.blogspot.turanukimaru.repos.BattleClassContent

/**
 * 戦闘シミュレータ起動アプリケーション
 */
class BattleSimApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
        //オブジェクト構文で作った奴をそのまま渡しても大丈夫.Realm.init()より後に評価される
        BattleUnitRepository.repo = BattleClassContent
    }
}