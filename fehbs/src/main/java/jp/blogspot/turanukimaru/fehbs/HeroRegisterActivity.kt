package jp.blogspot.turanukimaru.fehbs

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import jp.blogspot.turanukimaru.fehs.R

/**
 * An activity representing a single Sake detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * 登録画面。タブレットで2ペインでも動作するコードを基にしているが手元にタブレットが無い…
 */
class HeroRegisterActivity : AppCompatActivity() {
    /**
     * 初期化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_unit_register)
        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        // 画面回転時などにフラグメントの中身を戻すコード
        // Bundleは中断・強制終了時の状態を持っているらしい
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            val fragment = HeroRegisterFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.sake_detail_container, fragment)
                    .commit()
            //activityのコンテナにフラグメントを押し込んでいる。
        }
    }

    /**
     * 戻るボタン？の制御。前の画面に戻るコード
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //　戻るボタンで親に戻ることを登録
            val upIntent = NavUtils.getParentActivityIntent(this)
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                // up先のタスクがないので生成するなど
//                TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities()
            } else {
                // 存在してるのでそのままupする
                NavUtils.navigateUpTo(this, upIntent)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
