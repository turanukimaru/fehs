package jp.blogspot.turanukimaru.fehs

/**
 * リポジトリのインタフェース
 */
interface ModelObjectRepository<T> {
    /**
     * リスト読み込み。日付指定入れたけどkotlinはnull使うべきじゃないしどうすべきか
     */
    fun find(item: T): T?

    /**
     * 新規.どうしても初期値が要るがこれが一般化できない…！DTOを別に作るという選択肢はあるが…！
     */
    fun create(initialItem: T): T

    /**
     * 新規OR更新
     */
    fun createOrUpdate(item: T): T

    /**
     * 削除
     */
    fun delete(item: T): Int

    /**
     * 削除
     */
    fun deleteById(id: String): Int

    /**
     * 条件付き検索
     */
    fun complexQuery(item: T): List<T>

    /**
     * 全部リスト
     */
    fun allItems(): List<T>

    /**
     * Id指定して取得
     */
    fun getById(id: String): T?

    /**
     * Id指定して取得
     */
    fun getById(id: Int): T?
}