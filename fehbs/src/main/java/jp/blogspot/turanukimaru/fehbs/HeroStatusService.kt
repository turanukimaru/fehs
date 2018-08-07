package jp.blogspot.turanukimaru.fehbs

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Spinner
import jp.blogspot.turanukimaru.fehs.R
import org.jetbrains.anko.onClick

/**
 * Floating Status Calc ゲームと同時に起動するのでサービス
 */
class HeroStatusService : Service() {

    private val locale = LocaleAdapter(java.util.Locale.getDefault()).locale
    private val viewBuilder = ViewBuilder(locale)

    private var view: View? = null
    private var windowManager: WindowManager? = null
    private var dpScale: Int = 0
    private var y = 88
    private var touchedY = 0
    override fun onCreate() {
        super.onCreate()

        // dipを取得
        dpScale = resources.displayMetrics.density.toInt()
    }

    // API ver の高い奴はこっち。昔の奴だと動かないのでとりあえず昔のAPI叩く
//        val typeLayer = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
    private val typeLayer = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT

    private val windowParams
        get() = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                typeLayer,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                , PixelFormat.TRANSPARENT
        )

    /**
     *     他アプリからStartService()で起動される
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //多重起動避け
        if (view != null) return super.onStartCommand(intent, flags, startId)

        // inflaterの生成
        val layoutInflater = LayoutInflater.from(this)
        windowManager = applicationContext
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // background color を書いておかないと透けるので注意が必要。透けないようにする設定は見つからない。
        // 配置は考える必要があるけどサイズギリギリだから考えるだけ無駄かな…
        val params = windowParams
        params.gravity = Gravity.TOP or Gravity.END
        y = 88 * dpScale
        params.y = y

        // レイアウトファイルからInfalteするViewを作成
        view = layoutInflater.inflate(R.layout.unit_register_float, null)

        val spinnerWeapon = view!!.findViewById<Spinner>(R.id.spinner_weapon3)
        val spinnerMove = view!!.findViewById<Spinner>(R.id.spinner_move3)
        val spinnerListener = viewBuilder.getSpinnerChangeListener(view!!)
        spinnerWeapon.onItemSelectedListener = spinnerListener
        spinnerMove.onItemSelectedListener = spinnerListener

        view!!.findViewById<Spinner>(R.id.boonRadioButton).onItemSelectedListener = viewBuilder.boostsListener(view!!)
        view!!.findViewById<Spinner>(R.id.baneRadioButton).onItemSelectedListener = viewBuilder.boostsListener(view!!)
        view!!.findViewById<Spinner>(R.id.raritySpinner).onItemSelectedListener = viewBuilder.boostsListener(view!!)
        view!!.findViewById<Spinner>(R.id.targetRaritySpinner).onItemSelectedListener = viewBuilder.boostsListener(view!!)
        view!!.findViewById<Spinner>(R.id.levelBoostSpinner).onItemSelectedListener = viewBuilder.boostsListener(view!!)

        view!!.findViewById<Button>(R.id.close_button).onClick {
            val name = view!!.findViewById<Spinner>(R.id.baseUnitSpinner).selectedItem.toString()
            val boon = view!!.findViewById<Spinner>(R.id.boonRadioButton).selectedItem.toString()
            val bane = view!!.findViewById<Spinner>(R.id.baneRadioButton).selectedItem.toString()
            val rarity = view!!.findViewById<Spinner>(R.id.targetRaritySpinner).selectedItem.toString().toInt()
            HeroRepoReceiver.sendMessage(this, name, boon, bane, rarity)
        }

        view!!.setOnTouchListener { v, e ->
            if (e.action != ACTION_MOVE) {
                touchedY = e.rawY.toInt()
                true
            } else {
                val newParams = windowParams
                y += e.rawY.toInt() - touchedY
                newParams.gravity = Gravity.TOP or Gravity.END
                newParams.y = y
                windowManager!!.updateViewLayout(v, newParams)
                touchedY = e.rawY.toInt()
                true
            }
        }

        // Viewを画面上に追加
        windowManager!!.addView(view, params)

        return super.onStartCommand(intent, flags, startId)
    }

    //終了時にリソース開放
    override fun onDestroy() {
        super.onDestroy()
        Log.d("debug", "onDestroy")
        // Viewを削除
        windowManager!!.removeView(view)
        view = null
    }

    //インタフェースなので必須だが何もしない
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}