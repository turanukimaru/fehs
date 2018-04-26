package jp.blogspot.turanukimaru.fehbs

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Spinner
import jp.blogspot.turanukimaru.fehs.R
import org.jetbrains.anko.onClick


class HeroStatusService : Service() {

    private val locale = LocaleAdapter(java.util.Locale.getDefault()).locale
    private val viewBuilder = ViewBuilder(locale)

    private var view: View? = null
    private var windowManager: WindowManager? = null
    private var dpScale: Int = 0

    override fun onCreate() {
        super.onCreate()

        // dipを取得
        dpScale = resources.displayMetrics.density.toInt()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (view != null) {
            return super.onStartCommand(intent, flags, startId)
        }

        // inflaterの生成
        val layoutInflater = LayoutInflater.from(this)

//        val typeLayer = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        val typeLayer = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT


        windowManager = applicationContext
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                typeLayer,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                , PixelFormat.TRANSPARENT
        )

        // 配置は考える必要があるけどサイズギリギリだから考えるだけ無駄かな…
        params.gravity = Gravity.TOP or Gravity.END
        params.y = 88 * dpScale // 80dp

        // レイアウトファイルからInfalteするViewを作成
//        val nullParent: ViewGroup? = null
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
           val name =  view!!.findViewById<Spinner>(R.id.baseUnitSpinner).selectedItem.toString()
           val boon =  view!!.findViewById<Spinner>(R.id.boonRadioButton).selectedItem.toString()
           val bane =  view!!.findViewById<Spinner>(R.id.baneRadioButton).selectedItem.toString()
           val rarity =  view!!.findViewById<Spinner>(R.id.raritySpinner).selectedItem.toString().toInt()
            HeroRepoReceiver.sendMessage(this, name, boon, bane, rarity)
            //    stopSelf()
        }

        // Viewを画面上に追加
        windowManager!!.addView(view, params)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("debug", "onDestroy")
        // Viewを削除
        windowManager!!.removeView(view)
        view = null
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }
}