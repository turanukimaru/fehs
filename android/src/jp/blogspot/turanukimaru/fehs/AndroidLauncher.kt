package jp.blogspot.turanukimaru.fehs

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

/**
 * ゲーム起動準備。戦闘シミュレータからIntent飛ばしても動く
 */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Applicationを経由せずに動かすときはここで初期化することになる
        // Initialize Realm. Should only be done once when the application starts.
//        Realm.init(this)
//        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
//        Realm.setDefaultConfiguration(realmConfig)
        //オブジェクト構文で作った奴をそのまま渡しても大丈夫.遅延評価してるのかな？
//        BattleUnitRepository.repo = BattleClassContent

        //LibGDXの設定
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = false
        config.useCompass = false
        initialize(MyMyGdxGame(), config)
    }
}
