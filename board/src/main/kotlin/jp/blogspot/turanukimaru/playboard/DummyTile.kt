package jp.blogspot.turanukimaru.playboard

/**
 * ダミーのタイル。場所に意味（地形）が無いゲームではこれをセットすればよい。将棋とか。
 */
enum class DummyTile {
    None,
    Plane
}

/**
 * ダミーの駒。駒の種類が関係ないゲームやテストに使う。
 */
enum class DummyPiece {
    Dummy
}