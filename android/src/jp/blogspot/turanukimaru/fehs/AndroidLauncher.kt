package jp.blogspot.turanukimaru.fehs

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import jp.blogspot.turanukimaru.fieldrepos.BattleFieldContent

//import jp.blogspot.turanukimaru.fieldrepos.RealmBattleField

/**
 * ゲーム起動準備。戦闘シミュレータからIntent飛ばしても動く
 */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Applicationを経由せずに動かすときはここで初期化することになる
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this)
//        val realmConfig = RealmConfiguration.Builder().build()
        //RealmFieldModule() が無いと、 ~is not part of schema 例外が出ることがある。出ずに使えることもあるからややこしいが、モジュールが複数あるときは危ない
//        val realmConfig = RealmConfiguration.Builder().modules(RealmFieldModule(),Realm.getDefaultModule()).deleteRealmIfMigrationNeeded().build()
//        Realm.deleteRealm(realmConfig)
//        Realm.setDefaultConfiguration(realmConfig)
//        val r = Realm.getDefaultInstance()
//        r.executeTransaction{ realm-> realm.createObject<RealmBattleField>( 1)}

//        r.executeTransaction{ realm-> realm.createObject<RealmBattleField>( 1)}

//        Realm.deleteRealm(realmConfig)
        //オブジェクト構文で作った奴をそのまま渡しても大丈夫.遅延評価してるのかな？
//        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
//        Realm.setDefaultConfiguration(realmConfig)
//        // テスト中はマイグレーションが面倒なので全部削除...したいのだがなぜか消えないー。
//        val r = Realm.getDefaultInstance()
//        r.executeTransaction {
//            r.deleteAll()
//        }
//        BattleFieldContent.realm = r
        BattleFieldRepository.repo = BattleFieldContent

        //LibGDXの設定
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = false
        config.useCompass = false
        initialize(MyGdxGame(), config)
//        initialize(jp.blogspot.turanukimaru.fehs.shogi.MyShogiGame(), config)
    }
}
