package jp.blogspot.turanukimaru.shogi

/**
 * 地形。そのうち防御地形とかも作る。壊せる壁どうするかな・・・初期は一ユニット扱いするべきか
 */
enum class Ground(val label: String, val cost: Int) {
    P("PLAIN", 1),
    M("MOUNTAIN", 1),
    W("WOODS", 2),
    R("RIVER", 1),
    S("START", 1),//スタート地点。入れ替え可能にするために平地にしかできない。
    O("OUT", 99),//枠外
}