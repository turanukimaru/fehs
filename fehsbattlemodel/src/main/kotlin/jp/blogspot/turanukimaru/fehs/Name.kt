package jp.blogspot.turanukimaru.fehs

/**
 * Created by turanukimaru on 2017/12/23.
 *
 **/

enum class Name(val jp: String, val us: String, val tw: String) {
    NONE("", "", ""),
    アイク("アイク", "Ike", "艾克"),
    アイク__総選挙_("アイク（総選挙）", "Ike (Brave Heroes)", "艾克 (Brave Heroes)"),
    アイラ("アイラ", "Ayra", "艾伊拉"),
    アクア("アクア", "Azura", "阿庫婭"),
    アクア__舞踏祭_("アクア（舞踏祭）", "Azura (Performing Arts)", "阿庫婭 (Performing Arts)"),
    アサマ("アサマ", "Azama", "淺間"),
    アズール__舞踏祭_("アズール（舞踏祭）", "Inigo (Performing Arts)", "阿茲爾 (Performing Arts)"),
    アテナ("アテナ", "Athena", "雅典娜"),
    アベル("アベル", "Abel", "阿貝爾"),
    アメリア("アメリア", "Amelia", "亞美利雅"),
    アルフォンス("アルフォンス", "Alfonse", "阿爾馮斯"),
    アルム("アルム", "Alm", "阿雷武"),
    アルヴィス("アルヴィス", "Arvis", "阿爾維斯"),
    アンナ("アンナ", "Anna", "安娜"),
    アーダン("アーダン", "Arden", "亞丹"),
    ウェンディ("ウェンディ", "Gwendolyn", "溫蒂"),
    ウルスラ("ウルスラ", "Ursula", "烏爾斯拉"),
    エイリーク("エイリーク", "Eirika", "艾莉可"),
    エスト("エスト", "Est", "愛絲特"),
    エフィ("エフィ", "Faye", "艾菲"),
    エフラム("エフラム", "Ephraim", "艾夫拉姆"),
    エリウッド("エリウッド", "Eliwood", "艾利烏德"),
    エリンシア("エリンシア", "Elincia", "艾琳西亞"),
    エリーゼ("エリーゼ", "Elise", "艾麗澤"),
    エリーゼ__夏_("エリーゼ（夏）", "Elise (Summer)", "艾麗澤"),
    エルトシャン("エルトシャン", "Eldigan", "艾爾多尚"),
    エルフィ("エルフィ", "Effie", "艾爾菲"),
    オグマ("オグマ", "Ogma", "奧古馬"),
    オスカー("オスカー", "Oscar", "奧斯卡"),
    オボロ("オボロ", "Oboro", "朧"),
    オリヴィエ("オリヴィエ", "Olivia", "奧利薇"),
    オリヴィエ__舞踏祭_("オリヴィエ（舞踏祭）", "Olivia (Performing Arts)", "奧利薇 (Performing Arts)"),
    オルエン("オルエン", "Olwen", "歐爾玟"),
    オーディン("オーディン", "Odin", "奧丁"),
    カイン("カイン", "Cain", "凱因"),
    カゲロウ("カゲロウ", "Kagero", "陽炎"),
    カザハナ("カザハナ", "Hana", "風花"),
    カタリナ("カタリナ", "Katarina", "卡達麗娜"),
    カチュア("カチュア", "Catria", "卡秋雅"),
    カミュ("カミュ", "Camus", "卡謬"),
    カミラ("カミラ", "Camilla", "卡美拉"),
    カミラ__春_("カミラ（春）", "Camilla (Spring)", "卡美拉 (Spring)"),
    カムイ__夏_("カムイ（夏）", "Corrin(F) (Summer)", "神威 (Summer)"),
    カムイ__女_("カムイ（女）", "Corrin(F)", "神威(F)"),
    カムイ__男_("カムイ（男）", "Corrin(M)", "神威(M)"),

    カレル("カレル", "Karel", "卡列爾"),
    ガイア("ガイア", "Gaius", "蓋亞"),
    ガイア__夏_("ガイア（夏）", "Gaius (Summer)", "蓋亞 (Summer)"),
    ギュンター("ギュンター", "Gunter", "君特"),
    クライネ("クライネ", "Clarisse", "克萊涅"),
    クラリーネ("クラリーネ", "Clarine", "克拉莉涅"),
    クレア("クレア", "Clair", "克萊爾"),
    クレイン("クレイン", "Klein", "克萊因"),
    クレーベ("クレーベ", "Clive", "克雷貝"),
    クロム("クロム", "Chrom", "庫洛武"),
    クロム__冬_("クロム（冬）", "Chrom (Winter's Envoy)", "庫洛武 (Winter's Envoy)"),
    クロム__春_("クロム（春）", "Chrom (Spring)", "庫洛武 (Spring)"),
    グレイ("グレイ", "Gray", "格萊"),
    ゴードン("ゴードン", "Gordin", "哥頓"),
    サイゾウ("サイゾウ", "Saizo", "彩造"),
    サクラ("サクラ", "Sakura", "櫻"),
    サクラ__収穫祭_("サクラ（収穫祭）", "Sakura (Trick or Defeat)", "櫻 (Trick or Defeat)"),
    サナキ("サナキ", "Sanaki", "莎娜琪"),
    サーリャ("サーリャ", "Tharja", "撒拉"),
    サーリャ__冬_("サーリャ（冬）", "Tharja (Winter's Envoy)", "撒拉 (Winter's Envoy)"),
    シグルド("シグルド", "Sigurd", "西格爾特"),
    シグレ__舞踏祭_("シグレ（舞踏祭）", "Shigure (Performing Arts)", "詩格萊 (Performing Arts)"),
    シノノメ("シノノメ", "Shiro", "東雲"),
    シャニー("シャニー", "Shanna", "夏妮"),
    シャラ("シャラ", "Rhajat", "夏拉"),
    シャロン("シャロン", "Sharena", "夏蓉"),
    シャーロッテ__花嫁_("シャーロッテ（花嫁）", "Charlotte (Bride)", "夏洛特 (Bride)"),
    シーダ("シーダ", "Caeda", "希達"),
    シーダ__花嫁_("シーダ（花嫁）", "Caeda (Bride)", "希達 (Bride)"),
    シーマ("シーマ", "Sheena", "希瑪"),
    ジェイガン("ジェイガン", "Jagen", "傑剛"),
    ジェニー("ジェニー", "Genny", "傑妮"),
    ジャファル("ジャファル", "Jaffar", "賈法爾"),
    ジョルジュ("ジョルジュ", "Jeorge", "喬爾裘"),
    ジョーカー("ジョーカー", "Jakob", "喬克"),
    ジョーカー__収穫祭_("ジョーカー（収穫祭）", "Jakob (Trick or Defeat)", "喬克 (Trick or Defeat)"),
    ジークベルト("ジークベルト", "Siegbert", "齊格貝爾特"),
    セシリア("セシリア", "Cecilia", "瑟西莉亞"),
    セツナ("セツナ", "Setsuna", "剎那"),
    セネリオ("セネリオ", "Soren", "賽涅里歐"),
    セリカ("セリカ", "Celica", "賽莉卡"),
    セリス("セリス", "Seliph", "賽列斯"),
    セルジュ("セルジュ", "Cherche", "塞爾裘"),
    セーバー("セーバー", "Saber", "賽巴"),
    セーラ("セーラ", "Serra", "賽拉"),
    ゼト("ゼト", "Seth", "賽特"),
    ゼフィール("ゼフィール", "Zephiel", "賽菲爾"),
    ゼロ("ゼロ", "Niles", "零"),
    ソニア("ソニア", "Sonya", "索尼婭"),
    ソフィーヤ("ソフィーヤ", "Sophia", "蘇菲亞"),
    ソレイユ("ソレイユ", "Soleil", "索雷優"),
    ソワレ("ソワレ", "Sully", "索瓦蕾"),
    ソール("ソール", "Stahl", "索爾"),

    タクミ("タクミ", "Takumi", "拓海"),
    ターナ("ターナ", "Tana", "塔娜"),
    チキ__夏_("チキ（夏）", "Tiki(A) (Summer)", "琪姬(A) (Summer)"),
    チキ__大人_("チキ（大人）", "Tiki(A)", "琪姬(A)"),
    チキ__幼_("チキ（幼）", "Tiki(Y)", "琪姬(Y)"),
    ツバキ("ツバキ", "Subaki", "椿"),
    ティアマト("ティアマト", "Titania", "狄亞馬特"),
    ティアモ("ティアモ", "Cordelia", "蒂亞莫"),
    ティアモ__花嫁_("ティアモ（花嫁）", "Cordelia (Bride)", "蒂亞莫 (Bride)"),
    ティルテュ("ティルテュ", "Tailtiu", "提爾透"),
    ディアドラ("ディアドラ", "Deirdre", "迪雅朵拉"),
    デューテ("デューテ", "Delthea", "迪優特"),
    ドニ("ドニ", "Donnel", "多尼"),
    ドルカス("ドルカス", "Dorcas", "多爾卡斯"),
    ドーガ("ドーガ", "Draug", "多加"),
    ナバール("ナバール", "Navarre", "拿巴爾"),
    ナーシェン("ナーシェン", "Narcian", "拿榭恩"),
    ニニアン("ニニアン", "Ninian", "尼尼安"),
    ニノ("ニノ", "Nino", "妮諾"),
    ネフェニー("ネフェニー", "Nephenee", "涅菲妮"),
    ノノ("ノノ", "Nowi", "儂儂"),
    ノノ__収穫祭_("ノノ（収穫祭）", "Nowi (Trick or Defeat)", "儂儂 (Trick or Defeat)"),
    ハロルド("ハロルド", "Arthur", "哈洛德"),
    バアトル("バアトル", "Bartre", "巴多爾"),
    バーツ("バーツ", "Barst", "巴茲"),
    パオラ("パオラ", "Palla", "帕奧拉"),
    ヒナタ("ヒナタ", "Hinata", "日向"),
    ヒノカ("ヒノカ", "Hinoka", "火乃香"),
    ヒーニアス("ヒーニアス", "Innes", "西尼亞斯"),
    ピエリ("ピエリ", "Peri", "皮艾莉"),
    ファ("ファ", "Fae", "法"),
    フィヨルム("フィヨルム", "Fjorm", "菲約爾姆"),
    フィル("フィル", "Fir", "菲爾"),
    フェリシア("フェリシア", "Felicia", "菲利西亞"),
    フレデリク("フレデリク", "Frederick", "弗雷德里克"),
    フレデリク__夏_("フレデリク（夏）", "Frederick (Summer)", "弗雷德里克 (Summer)"),
    フロリーナ("フロリーナ", "Florina", "芙羅利娜"),
    プリシラ("プリシラ", "Priscilla", "普莉西亞"),
    ヘクトル("ヘクトル", "Hector", "海克托爾"),
    ヘンリー("ヘンリー", "Henry", "亨利"),
    ヘンリー__収穫祭_("ヘンリー（収穫祭）", "Henry (Trick or Defeat)", "亨利 (Trick or Defeat)"),
    ベルカ("ベルカ", "Beruka", "貝爾卡"),
    ベルクト("ベルクト", "Berkut", "貝爾克特"),
    ホークアイ("ホークアイ", "Hawkeye", "霍克艾"),
    ボーイ("ボーイ", "Boey", "波伊"),
    マシュー("マシュー", "Matthew", "馬修"),
    マチルダ("マチルダ", "Mathilda", "瑪蒂爾達"),
    マリア("マリア", "Maria", "瑪莉亞"),
    マリク("マリク", "Merric", "馬利克"),
    マルス("マルス", "Marth", "馬爾斯"),
    マルス__仮面_("マルス（仮面）", "Marth (Masked)", "馬爾斯 (Masked)"),
    マークス("マークス", "Xander", "馬克斯"),
    マークス__夏_("マークス（夏）", "Xander (Summer)", "馬克斯 (Summer)"),
    マークス__春_("マークス（春）", "Xander (Spring)", "馬克斯 (Spring)"),
    ミシェイル("ミシェイル", "Michalis", "米謝爾"),
    ミスト("ミスト", "Mist", "密絲特"),
    ミネルバ("ミネルバ", "Minerva", "米奈娃"),
    メイ("メイ", "Mae", "梅伊"),
    ユリア("ユリア", "Julia", "尤莉亞"),
    ヨシュア("ヨシュア", "Joshua", "約書亞"),
    ラインハルト("ラインハルト", "Reinhardt", "萊茵哈特"),
    ラケシス("ラケシス", "Lachesis", "拉克西絲"),
    ラズワルド("ラズワルド", "Laslow", "拉茲沃德"),
    リズ("リズ", "Lissa", "莉茲"),
    リズ__冬_("リズ（冬）", "Lissa (Winter's Envoy)", "莉茲 (Winter's Envoy)"),
    リフ("リフ", "Wrys", "利夫"),
    リョウマ("リョウマ", "Ryoma", "龍馬"),
    リリーナ("リリーナ", "Lilina", "莉莉娜"),
    リン("リン", "Lyn", "琳"),
    リンダ("リンダ", "Linde", "琳達"),
    リン__総選挙_("リン（総選挙）", "Lyn (Brave Heroes)", "琳"),
    リン__花嫁_("リン（花嫁）", "Lyn (Bride)", "琳 (Bride)"),
    ルカ("ルカ", "Lukas", "盧卡"),
    ルキナ("ルキナ", "Lucina", "露琪娜"),
    ルキナ__春_("ルキナ（春）", "Lucina (Spring)", "露琪娜"),
    ルキナ__総選挙_("ルキナ（総選挙）", "Lucina (Brave Heroes)", "露琪娜 (Brave Heroes)"),
    ルセア("ルセア", "Lucius", "魯賽亞"),
    ルフレ__冬_("ルフレ（冬）", "Robin (M) (Winter's Envoy)", "魯弗萊 (Winter's Envoy)"),
    ルフレ__夏_("ルフレ（夏）", "Robin(F) (Summer)", "魯弗萊 (Summer)"),
    ルフレ__女_("ルフレ（女）", "Robin(F)", "魯弗萊(F)"),
    ルフレ__男_("ルフレ（男）", "Robin(M)", "魯弗萊(M)"),
    ルーク("ルーク", "Luke", "路克"),
    ルーテ("ルーテ", "Lute", "盧特"),
    ルーナ("ルーナ", "Selena", "露娜"),
    レイ("レイ", "Raigh", "雷伊"),
    レイヴァン("レイヴァン", "Raven", "瑞文"),
    レオ("レオ", "Leon", "雷歐"),
    レオン("レオン", "Leo", "里昂"),
    レオン__夏_("レオン（夏）", "Leo (Summer)", "里昂"),
    レベッカ("レベッカ", "Rebecca", "蕾貝卡"),
    ロイ("ロイ", "Roy", "羅伊"),
    ロイド("ロイド", "Lloyd", "洛伊德"),
    ロイ__総選挙_("ロイ（総選挙）", "Roy (Brave Heroes)", "羅伊 (Brave Heroes)"),
    ロディ("ロディ", "Roderick", "羅迪"),
    ロビン("ロビン", "Tobin", "羅賓"),
    ロンクー("ロンクー", "Lonqu", "隆克"),
    ローロー("ローロー", "Legion", "羅羅"),
    ワユ("ワユ", "Mia", "瓦育"),
    ヴァルター("ヴァルター", "Valter", "瓦爾達"),
    ヴィオール("ヴィオール", "Virion", "維沃爾"),
    漆黒の騎士("漆黒の騎士", "Black Knight", "漆黑騎士"),

    Absorb("アブゾーブ", "Absorb", "吸收"),
    Absorb2("アブゾーブ＋", "Absorb+", "吸收＋"),
    Alondite("エタルド", "Alondite", "艾塔爾德"),
    Amiti("アミーテ", "Amiti", "亞米特"),
    Armoads("アルマーズ", "Armoads", "阿爾瑪茲"),
    ArmorSlayer("アーマーキラー", "Armor Slayer", "破甲劍"),
    ArmorSlayer2("アーマーキラー＋", "Armor Slayer+", "破甲劍＋"),
    Armorsmasher("アーマーキラー鍛", "Armorsmasher+", "破甲劍鍛"),
    Armorsmasher2("アーマーキラー鍛＋", "Armorsmasher+", "破甲劍鍛＋"),
    AssassinsBow("暗器殺しの弓", "Assassins Bow", "破暗器之弓"),
    AssassinsBow2("暗器殺しの弓＋", "Assassins Bow+", "破暗器之弓＋"),
    Assault("アサルト", "Assault", "襲擊"),
    Audhulma("アウドムラ", "Audhulma", "奧德姆拉"),
    Aura("オーラ", "Aura", "輝光"),
    AyrasBlade("瞬閃アイラの剣", "Ayras Blade", "瞬閃艾伊拉之劍"),
    BerkutsLance("ベルクトの槍", "Berkuts Lance", "貝爾克特之槍"),
    BerkutsLance2("ベルクトの槍＋", "Berkuts Lance+", "貝爾克特之槍＋"),
    BindingBlade("封印の剣", "Binding Blade", "封印之劍"),
    Blarblade("ブラーブレード", "Blárblade", "蒼藍之刃"),
    Blarblade2("ブラーブレード＋", "Blárblade+", "蒼藍之刃＋"),
    Blarowl("ブラーアウル", "Blárowl", "蒼藍夜梟"),
    Blarowl2("ブラーアウル＋", "Blárowl+", "蒼藍夜梟＋"),
    Blarraven("ブラーレイヴン", "Blárraven", "蒼藍渡鴉"),
    Blarraven2("ブラーレイヴン＋", "Blárraven+", "蒼藍渡鴉＋"),
    Blarwolf("ブラーウルフ", "Blarwolf", "蒼藍餓狼"),
    Blarwolf2("ブラーウルフ＋", "Blarwolf+", "蒼藍餓狼＋"),
    BlazingDurandal("烈剣デュランダル", "Blazing Durandal", "烈劍迪朗達爾"),
    BlessedBouquet("聖なるブーケ", "Blessed Bouquet", "聖潔花束"),
    BlessedBouquet2("聖なるブーケ＋", "Blessed Bouquet+", "聖潔花束＋"),
    BlueEgg("青の卵", "Blue Egg", "青之卵"),
    BlueEgg2("青の卵＋", "Blue Egg+", "青之卵＋"),
    Bolganone("ボルガノン", "Bolganone", "火山爆焰"),
    Bolganone2("ボルガノン＋", "Bolganone+", "火山爆焰＋"),
    BraveAxe("勇者の斧", "Brave Axe", "勇者之斧"),
    BraveAxe2("勇者の斧＋", "Brave Axe+", "勇者之斧＋"),
    BraveBow("勇者の弓", "Brave Bow", "勇者之弓"),
    BraveBow2("勇者の弓＋", "Brave Bow+", "勇者之弓＋"),
    BraveLance("勇者の槍", "Brave Lance", "勇者之槍"),
    BraveLance2("勇者の槍＋", "Brave Lance+", "勇者之槍＋"),
    BraveSword("勇者の剣", "Brave Sword", "勇者之劍"),
    BraveSword2("勇者の剣＋", "Brave Sword+", "勇者之劍＋"),
    BrightNaginata("白き血の薙刀", "Bright Naginata", "白血薙刀"),
    Brynhildr("ブリュンヒルデ", "Brynhildr", "布倫希爾德"),
    Candelabra("燭台", "Candelabra", "燭台"),
    Candelabra2("燭台＋", "Candelabra+", "燭台＋"),
    Candlelight("キャンドルサービス", "Candlelight", "燭光"),
    Candlelight2("キャンドルサービス＋", "Candlelight+", "燭光＋"),
    CarrotAxe("ニンジンの斧", "Carrot Axe", "胡蘿蔔斧"),
    CarrotAxe2("ニンジンの斧＋", "Carrot Axe+", "胡蘿蔔斧＋"),
    CarrotLance("ニンジンの槍", "Carrot Lance", "胡蘿蔔槍"),
    CarrotLance2("ニンジンの槍＋", "Carrot Lance+", "胡蘿蔔槍＋"),
    ClarissesBow("クライネの弓", "Clarisses Bow", "克萊涅之弓"),
    ClarissesBow2("クライネの弓＋", "Clarisses Bow+", "克萊涅之弓＋"),
    CupidArrow("キューピッドの矢", "Cupid Arrow", "邱比特之弓"),
    CupidArrow2("キューピッドの矢＋", "Cupid Arrow+", "邱比特之弓＋"),
    CursedLance("魔性の槍", "Cursed Lance", "魔性之槍"),
    Cymbeline("シムベリン", "Cymbeline", "辛白林"),
    DancersFan("舞踏祭の扇子", "Dancer's Fan", "舞會摺扇"),
    DancersFan2("舞踏祭の扇子＋", "Dancer's Fan+", "舞會摺扇＋"),
    DancersRing("舞踏祭の輪", "Dancer's Ring", "舞會之環"),
    DancersRing2("舞踏祭の輪＋", "Dancer's Ring+", "舞會之環＋"),
    DancersScore("舞踏祭の楽譜", "Dancer's Score", "舞會樂章"),
    DancersScore2("舞踏祭の楽譜＋", "Dancer's Score+", "舞會樂章＋"),
    DarkAura("共鳴オーラ", "Dark Aura", "共鳴輝光"),
    DarkBreath("闇のブレス", "Dark Breath", "暗之吐息"),
    DarkBreath2("闇のブレス＋", "Dark Breath+", "暗之吐息＋"),
    DarkExcalibur("共鳴エクスカリバー", "Dark Excalibur", "共鳴埃克斯卡利伯"),
    DarkGreatsword("黒き血の大剣", "Dark Greatsword", "黑血大劍"),
    DeathlyDagger("死神の暗器", "Deathly Dagger", "死神暗器"),
    DeftHarpoon("魚を突いた銛", "Deft Harpoon", "捕魚叉"),
    DeftHarpoon2("魚を突いた銛＋", "Deft Harpoon+", "捕魚叉＋"),
    DireThunder("ダイムサンダ", "Dire Thunder", "神雷"),
    DivineNaga("聖書ナーガ", "Divine Naga", "聖書那迦"),
    DivineTyrfing("聖剣ティルフィング", "Divine Tyrfing", "聖劍斬裂劍"),
    Durandal("デュランダル", "Durandal", "迪朗達爾"),
    Eckesachs("エッケザックス", "Eckesachs", "艾坎札克斯"),
    Elfire("エルファイアー", "Elfire", "狂焰"),
    Elivagar("エリヴァーガル", "Elivagar", "Elivagar"),
    Elthunder("エルサンダー", "Elthunder", "狂雷"),
    Elwind("エルウィンド", "Elwind", "狂風"),
    EmeraldAxe("深緑の斧", "Emerald Axe", "深綠之斧"),
    EmeraldAxe2("深緑の斧＋", "Emerald Axe+", "深綠之斧＋"),
    Excalibur("エクスカリバー", "Excalibur", "埃克斯卡利伯"),
    Falchion("ファルシオン", "Falchion", "法爾西昂"),
    Fear("フィアー", "Fear", "恐懼"),
    Fear2("フィアー＋", "Fear+", "恐懼＋"),
    Fenrir("ノスフェラート", "Fenrir", "地獄之炎"),
    Fenrir2("ノスフェラート＋", "Fenrir+", "地獄之炎＋"),
    Fensalir("フェンサリル", "Fensalir", "芬薩里爾"),
    Fire("ファイアー", "Fire", "火焰"),
    FireBreath("火のブレス", "Fire Breath", "火之吐息"),
    FireBreath2("火炎のブレス", "Fire Breath+", "火焰吐息"),
    FiresweepBow("火薙ぎの弓", "Firesweep Bow", "火薙之弓"),
    FiresweepBow2("火薙ぎの弓＋", "Firesweep Bow+", "火薙之弓＋"),
    FiresweepLance("火薙ぎの槍", "Firesweep Lance", "火薙之槍"),
    FiresweepLance2("火薙ぎの槍＋", "Firesweep Lance+", "火薙之槍＋"),
    FiresweepSword("火薙ぎの剣", "Firesweep Sword", "火薙之劍"),
    FiresweepSword2("火薙ぎの剣＋", "Firesweep Sword+", "火薙之劍＋"),
    FirstBite("ファーストバイト", "First Bite", "定情之槍"),
    FirstBite2("ファーストバイト＋", "First Bite+", "定情之槍＋"),
    Flametongue("灼熱のブレス", "Flametongue", "灼熱吐息"),
    Flametongue2("灼熱のブレス＋", "Flametongue+", "灼熱吐息"),
    Flux("ミィル", "Flux", "流動"),
    Folkvangr("フォルクヴァング", "Folkvangr", "弗爾克范格"),
    FujinYumi("風神弓", "FujinYumi", "風神弓"),
    Geirskogul("ゲイルスケグル", "Geirskogul", "潔爾詩科古爾"),
    Gradivus("グラディウス", "Gradivus", "古拉迪烏斯"),
    Gravity("グラビティ", "Gravity", "重壓"),
    Gravity2("グラビティ＋", "Gravity+", "重壓＋"),
    GreenEgg("緑の卵", "Green Egg", "綠之卵"),
    GreenEgg2("緑の卵＋", "Green Egg+", "綠之卵＋"),
    Grimoire("グリモワール", "Grimoire", "魔法書"),
    Gronnblade("グルンブレード", "Gronnblade", "翠綠之刃"),
    Gronnblade2("グルンブレード＋", "Gronnblade+", "翠綠之刃＋"),
    Gronnowl("グルンアウル", "Gronnowl", "翠綠夜梟"),
    Gronnowl2("グルンアウル＋", "Gronnowl+", "翠綠夜梟＋"),
    Gronnraven("グルンレイヴン", "Gronnraven", "翠綠渡鴉"),
    Gronnraven2("グルンレイヴン＋", "Gronnraven+", "翠綠渡鴉＋"),
    Gronnwolf("グルンウルフ", "Gronnwolf", "翠綠餓狼"),
    Gronnwolf2("グルンウルフ＋", "Gronnwolf+", "翠綠餓狼＋"),
    GuardBow("遠距離防御の弓", "Guard  Bow", "遠距離防禦之弓"),
    GuardBow2("遠距離防御の弓＋", "Guard  Bow+", "遠距離防禦之弓＋"),
    Hammer("ハンマー", "Hammer", "戰錘"),
    Hammer2("ハンマー＋", "Hammer+", "戰錘＋"),
    Handbell("ハンドベル", "Handbell", "手鐘"),
    Handbell2("ハンドベル＋", "Handbell+", "手鐘＋"),
    Hauteclere("オートクレール", "Hauteclere", "奧特克雷勒"),
    HeavySpear("貫きの槍", "Heavy Spear", "貫穿之槍"),
    HeavySpear2("貫きの槍＋", "Heavy Spear+", "貫穿之槍＋"),
    HibiscusTome("ハイビスカスの本", "Hibiscus Tome", "扶桑花之書"),
    HibiscusTome2("ハイビスカスの本＋", "Hibiscus Tome+", "扶桑花之書＋"),
    IronAxe("鉄の斧", "Iron Axe", "鐵斧"),
    IronBow("鉄の弓", "Iron Bow", "鐵弓"),
    IronDagger("鉄の暗器", "Iron Dagger", "鐵暗器"),
    IronLance("鉄の槍", "Iron Lance", "鐵槍"),
    IronSword("鉄の剣", "Iron Sword", "鐵劍"),
    KeenBlarwolf("ブラーウルフ鍛", "Keen Blarwolf", "蒼藍餓狼鍛"),
    KeenBlarwolf2("ブラーウルフ鍛＋", "Keen Blárwolf+", "蒼藍餓狼鍛＋"),
    KeenGronnwolf("グルンウルフ鍛", "Keen Gronnwolf", "翠綠餓狼鍛"),
    KeenGronnwolf2("グルンウルフ鍛＋", "Keen Gronnwolf+", "翠綠餓狼鍛＋"),
    KeenRauorwolf("ラウアウルフ鍛", "Keen Rauðrwolf", "赤紅餓狼鍛"),
    KeenRauorwolf2("ラウアウルフ鍛＋", "Keen Rauðrwolf+", "赤紅餓狼鍛＋"),
    KillerAxe("キラーアクス", "Killer Axe", "必殺斧"),
    KillerAxe2("キラーアクス＋", "Killer Axe+", "必殺斧＋"),
    KillerBow("キラーボウ", "Killer Bow", "必殺弓"),
    KillerBow2("キラーボウ＋", "Killer Bow+", "必殺弓＋"),
    KillerLance("キラーランス", "Killer Lance", "必殺槍"),
    KillerLance2("キラーランス＋", "Killer Lance+", "必殺槍＋"),
    KillingEdge("キルソード", "Killing Edge", "必殺劍"),
    KillingEdge2("キルソード＋", "Killing Edge+", "必殺劍＋"),
    KittyPaddle("猫の暗器", "Kitty Paddle", "小貓暗器"),
    KittyPaddle2("猫の暗器＋", "Kitty Paddle+", "小貓暗器＋"),
    LegionsAxe("ローローの斧", "Legions Axe", "羅羅之斧"),
    LegionsAxe2("ローローの斧＋", "Legions Axe+", "羅羅之斧＋"),
    Leiptr("レイプト", "Leiptr", "雷依普特"),
    LightBreath("光のブレス", "Light Breath", "光之吐息"),
    LightBreath2("光のブレス＋", "Light Breath+", "光之吐息＋"),
    LightningBreath("雷のブレス", "Lightning Breath", "雷之吐息"),
    LightningBreath2("雷のブレス＋", "Lightning Breath+", "雷之吐息＋"),
    LilithFloatie("リリスの浮き輪", "Lilith Floatie", "莉莉絲的浮排"),
    LilithFloatie2("リリスの浮き輪＋", "Lilith Floatie+", "莉莉絲的浮排＋"),
    MelonCrusher("スイカ割りの棍棒", "MelonCrusher", "打西瓜的棍棒"),
    MelonCrusher2("スイカ割りの棍棒＋", "MelonCrusher+", "打西瓜的棍棒＋"),
    MonstrousBow("怪物の弓", "Monstrous Bow", "怪物弓"),
    MonstrousBow2("怪物の弓＋", "Monstrous Bow+", "怪物弓＋"),
    HamaYa("破魔矢", "Hama Ya", "Hama Ya"),
    HamaYa2("破魔矢＋", "Hama Ya+", "Hama Ya+"),
    Mulagir("ミュルグレ", "Mulagir", "妙爾葛雷"),
    Mystletainn("ミストルティン", "Mystletainn", "米斯特汀"),
    Naga("ナーガ", "Naga", "那迦"),
    Nidhogg("ニーズヘッグ", "Nidhogg", "尼德霍格"),
    Noatun("ノーアトゥーン", "Noatun", "諾歐通"),
    Pain("ペイン", "Pain", "疼痛"),
    Pain2("ペイン＋", "Pain+", "疼痛＋"),
    Panic("パニック", "Panic", "慌亂"),
    Panic2("パニック＋", "Panic+", "慌亂＋"),
    Parthia("パルティア", "Parthia", "帕提亞"),
    PoisonDagger("秘毒の暗器", "Poison Dagger", "秘毒暗器"),
    PoisonDagger2("秘毒の暗器＋", "Poison Dagger+", "秘毒暗器＋"),
    Ragnarok("ライナロック", "Ragnarok", "末世烈焰"),
    Ragnell("ラグネル", "Ragnell", "ラグネル"),
    Raijinto("雷神刀", "Raijinto", "雷神刀"),
    Rauorblade("ラウアブレード", "Rauðrblade", "赤紅之刃"),
    Rauorblade2("ラウアブレード＋", "Rauðrblade+", "赤紅之刃"),
    Rauorowl("ラウアアウル", "Rauðrowl", "赤紅夜梟"),
    Rauorowl2("ラウアアウル＋", "Rauðrowl+", "赤紅夜梟＋"),
    Rauorraven("ラウアレイヴン", "Rauðrraven", "赤紅渡鴉"),
    Rauorraven2("ラウアレイヴン＋", "Rauðrraven+", "赤紅渡鴉＋"),
    Rauorwolf("ラウアウルフ", "Rauðrwolf", "赤紅餓狼"),
    Rauorwolf2("ラウアウルフ＋", "Rauðrwolf+", "赤紅餓狼＋"),
    RefreshingBolt("氷菓子の弓", "Refreshing Bolt", "冰品弓"),
    RefreshingBolt2("氷菓子の弓＋", "Refreshing Bolt+", "冰品弓＋"),
    RegalBlade("リガルブレイド", "Regal Blade", "帝王之劍"),
    ResoluteBlade("気鋭ワユの剣", "Resolute Blade", "氣銳瓦育之劍"),
    Rexcalibur("レクスカリバー", "Rexcalibur", "列克斯卡利伯"),
    Rexcalibur2("レクスカリバー＋", "Rexcalibur+", "列克斯卡利伯＋"),
    Ridersbane("ホースキラー", "Ridersbane", "殺馬槍"),
    Ridersbane2("ホースキラー＋", "Ridersbane+", "殺馬槍＋"),
    RogueDagger("盗賊の暗器", "Rogue Dagger", "盜賊暗器"),
    RogueDagger2("盗賊の暗器＋", "Rogue Dagger+", "盜賊暗器＋"),
    RubySword("旭日の剣", "Ruby Sword", "旭日之劍"),
    RubySword2("旭日の剣＋", "Ruby Sword+", "旭日之劍＋"),
    Ruin("ルイン", "Ruin", "破滅"),
    SackOGifts("プレゼント袋", "Sack o' Gifts", "禮物袋"),
    SackOGifts2("プレゼント袋＋", "Sack o' Gifts+", "禮物袋"),
    SapphireLance("蒼海の槍", "Sapphire Lance", "蒼海之槍"),
    SapphireLance2("蒼海の槍＋", "Sapphire Lance+", "蒼海之槍＋"),
    SealifeTome("熱帯魚の本", "Sealife Tome", "熱帶魚之書"),
    SealifeTome2("熱帯魚の本＋", "Sealife Tome+", "熱帶魚之書＋"),
    Seashell("貝殻", "Seashell", "貝殼"),
    Seashell2("貝殻＋", "Seashell+", "貝殼＋"),
    Siegfried("ジークフリート", "Siegfried", "西格弗里德"),
    Sieglinde("ジークリンデ", "Sieglinde", "齊格林德"),
    Siegmund("ジークムント", "Siegmund", "齊格蒙德"),
    SilverAxe("銀の斧", "Silver Axe", "銀斧"),
    SilverAxe2("銀の斧＋", "Silver Axe+", "銀斧＋"),
    SilverBow("銀の弓", "Silver Bow", "銀弓"),
    SilverBow2("銀の弓＋", "Silver Bow+", "銀弓＋"),
    SilverDagger("銀の暗器", "Silver Dagger", "銀暗器"),
    SilverDagger2("銀の暗器＋", "Silver Dagger+", "銀暗器＋"),
    SilverLance("銀の槍", "Silver Lance", "銀槍"),
    SilverLance2("銀の槍＋", "Silver Lance+", "銀槍＋"),
    SilverSword("銀の剣", "Silver Sword", "銀劍"),
    SilverSword2("銀の剣＋", "Silver Sword+", "銀劍＋"),
    SlayingAxe("キラーアクス鍛", "Slaying Axe", "必殺斧鍛"),
    SlayingAxe2("キラーアクス鍛＋", "Slaying Axe+", "必殺斧鍛＋"),
    SlayingBow("キラーボウ鍛", "Slaying Bow", "必殺弓鍛"),
    SlayingBow2("キラーボウ鍛＋", "Slaying Bow+", "必殺弓鍛＋"),
    SlayingEdge("キルソード鍛", "Slaying Edge", "必殺劍鍛"),
    SlayingEdge2("キルソード鍛＋", "Slaying Edge+", "必殺劍鍛＋"),
    SlayingHammer("ハンマー鍛", "Slaying Hammer+", "戰錘鍛"),
    SlayingHammer2("ハンマー鍛＋", "Slaying Hammer+", "戰錘鍛＋"),
    SlayingLance("キラーランス鍛", "Slaying Lance", "必殺槍鍛"),
    SlayingLance2("キラーランス鍛＋", "Slaying Lance+", "必殺槍鍛＋"),
    SlayingSpear("貫きの槍鍛", "Slaying Spear+", "貫穿之槍鍛"),
    SlayingSpear2("貫きの槍鍛＋", "Slaying Spear+", "貫穿之槍鍛＋"),
    Slow("スロウ", "Slow", "緩速"),
    Slow2("スロウ＋", "Slow+", "緩速＋"),
    SmokeDagger("紫煙の暗器", "Smoke Dagger", "紫煙暗器"),
    SmokeDagger2("紫煙の暗器＋", "Smoke Dagger+", "紫煙暗器＋"),
    SolKatti("ソール・カティ", "SolKatti", "索爾‧卡提"),
    SpectralTome("ゴーストの魔導書", "Spectral Tome", "惡靈魔道書"),
    SpectralTome2("ゴーストの魔導書+", "Spectral Tome+", "惡靈魔道書＋"),
    SteelAxe("鋼の斧", "Steel Axe", "鋼斧"),
    SteelBow("鋼の弓", "Steel Bow", "鋼弓"),
    SteelDagger("鋼の暗器", "Steel Dagger", "鋼暗器"),
    SteelLance("鋼の槍", "Steel Lance", "鋼槍"),
    SteelSword("鋼の剣", "Steel Sword", "鋼劍"),
    StoutTomahawk("剛斧トマホーク", "Stout  Tomahawk", "剛斧托馬霍克"),
    Tannenboom("聖樹", "Tannenboom", "聖樹"),
    Tannenboom2("聖樹＋", "Tannenboom!", "聖樹"),
    Thoron("トロン", "Thoron", "托隆"),
    Thoron2("トロン＋", "Thoron+", "托隆＋"),
    Thunder("サンダー", "Thunder", "雷電"),
    TomatoTome("トマトの本", "Tomato Tome", "番茄之書"),
    TomatoTome2("トマトの本＋", "Tomato Tome+", "番茄之書＋"),
    Tyrfing("ティルフィング", "Tyrfing", "斬裂劍"),
    Uror("ウルズ", "Uror", "烏爾德"),
    Urvan("ウルヴァン", "Urvan", "烏爾邦"),
    Valaskjalf("ヴラスキャルヴ", "Valaskjalf", "Valaskjalf"),
    Valflame("ファラフレイム", "Valflame", "法拉之炎"),
    Vidofinir("ヴィドフニル", "Vidofinir", "維德佛爾尼爾"),
    WaoDao("倭刀", "Wao Dao", "倭刀"),
    WaoDao2("倭刀＋", "Wao Dao+", "倭刀＋"),
    WeirdingTome("奇異ルーテの書", "Weirding Tome", "奇異盧特之書"),
    Wind("ウィンド", "Wind", "烈風"),
    Yato("夜刀神", "Yato", "夜刀神"),
    Zanbato("斬馬刀", "Zanbato", "斬馬刀"),
    Zanbato2("斬馬刀＋", "Zanbato+", "斬馬刀＋"),

    ArdentSacrifice("献身", "Ardent Sacrifice", "獻身"),
    Dance("踊る", "Dance", "Dance"),
    DrawBack("引き寄せ", "Draw Back", "拉近"),
    HarshCommand("一喝", "Harsh Command", "大喝"),
    Heal("ライブ", "Heal", "聖療"),
    Martyr("セインツ", "Martyr", "殉教"),
    Martyr2("セインツ＋", "Martyr+", "殉教＋"),
    Mend("リライブ", "Mend", "大聖療"),
    Physic("リブロー", "Physic", "遠程聖療"),
    Physic2("リブロー＋", "Physic+", "遠程聖療＋"),
    Pivot("回り込み", "Pivot", "迂迴"),
    RallyAtkDef("攻撃守備の応援", "Rally Attack Defense", "攻撃防守支援"),
    RallyAtkRes("攻撃魔防の応援", "Rally Attack Resistance", "攻撃魔防支援"),
    RallyAtkSpd("攻撃速さの応援", "Rally Attack Speed", "攻撃速度支援"),
    RallyAttack("攻撃の応援", "Rally Attack", "攻撃支援"),
    RallyDefRes("守備魔防の応援", "Rally Defense Resistance", "防守魔防支援"),
    RallyDefense("守備の応援", "Rally Defense", "防守支援"),
    RallyResistance("魔防の応援", "Rally Resistance", "魔防支援"),
    RallySpdDef("速さ守備の応援", "Rally Speed Defense", "速度防守支援"),
    RallySpdRes("速さ魔防の応援", "Rally Speed Resistance", "速度魔防支援"),
    RallySpeed("速さの応援", "Rally Speed", "速度"),
    ReciprocalAid("相互援助", "Reciprocal Aid", "相互援助"),
    Reconcile("ヒール", "Reconcile", "治療"),
    Recover("リカバー", "Recover", "聖癒"),
    Recover2("リカバー＋", "Recover+", "聖癒＋"),
    Rehabilitate("リバース", "Rehabilitate", "重生"),
    Rehabilitate2("リバース＋", "Rehabilitate+", "重生＋"),
    Reposition("引き戻し", "Reposition", "帶回"),
    Shove("体当たり", "Shove", "衝撞"),
    Sing("歌う", "Sing", "Sing"),
    Smite("ぶちかまし", "Smite", "重撃"),
    Swap("入れ替え", "Swap", "替換"),

    Daylight("陽影", "Daylight", "黎明"),
    Aegis("聖盾", "Aegis", "聖盾"),
    Aether("天空", "Aether", "天空"),
    Astra("流星", "Astra", "流星"),
    BlackLuna("黒の月光", "Black Luna", "黒の月光"),
    BlazingFlame("烈火", "Blazing Flame", "烈火"),
    BlazingLight("烈光", "Blazing Light", "烈光"),
    BlazingThunder("烈雷", "Blazing Thunder", "烈雷"),
    BlazingWind("烈風", "Blazing Wind", "強風"),
    Bonfire("緋炎", "Bonfire", "緋炎"),
    Buckler("小盾", "Buckler", "小盾"),
    ChillingWind("氷点", "Chilling Wind", "冰點"),
    DragonGaze("伏竜", "Dragon Gaze", "臥龍"),
    DragonFang("竜穿", "Dragon Fang", "竜穿"),
    DraconicAura("竜裂", "Draconic Aura", "竜裂"),
    Escutcheon("長盾", "Escutcheon", "長盾"),
    Galeforce("疾風迅雷", "Galeforce", "疾風迅雷"),
    Glacies("氷華", "Glacies", "氷華"),
    Glimmer("凶星", "Glimmer", "凶星"),
    GlowingEmber("蛍火", "Glowing Ember", "蛍*火"),
    GrowingFlame("爆火", "Growing Flame", "爆火"),
    GrowingLight("爆光", "Growing Light", "爆光"),
    GrowingThunder("爆雷", "Growing Thunder", "爆雷"),
    GrowingWind("爆風", "Growing Wind", "爆風"),
    HeavenlyLight("天照", "Heavenly Light", "天照"),
    HolyVestments("聖衣", "Holy Vestments", "聖衣"),
    IceMirror("氷の聖鏡", "Ice Mirror", "氷の聖鏡"),
    Iceberg("氷蒼", "Iceberg", "冰蒼"),
    Ignis("華炎", "Ignis", "華炎"),
    Imbue("治癒", "Imbue", "治癒"),
    KindledFireBalm("業火の祝福", "Kindled Fire Balm", "業火的祝福"),
    Luna("月光", "Luna", "月光"),
    Miracle("祈り", "Miracle", "祈禱"),
    Moonbow("月虹", "Moonbow", "月虹"),
    NewMoon("影月", "NewMoon", "新月"),
    NightSky("星影", "NightSky", "星影"),
    Noontime("夕陽", "Noontime", "夕陽"),
    Pavise("大盾", "Pavise", "大盾"),
    RegnalAstra("剣姫の流星", "Regnal Astra", "劍姫的流星"),
    Reprisal("血讐", "Reprisal", "血仇"),
    Retribution("雪辱", "Retribution", "雪辱"),
    RisingFlame("砕火", "Rising Flame", "碎火"),
    RisingLight("砕光", "Rising Light", "碎光"),
    RisingThunder("砕雷", "Rising Thunder", "碎雷"),
    RisingWind("砕風", "Rising Wind", "碎風"),
    SacredCowl("聖兜", "Sacred Cowl", "甲"),
    Sol("太陽", "Sol", "太陽"),
    SolidEarthBalm("大地の祝福", "Solid Earth Balm", "大地的祝福"),
    StillWaterBalm("静水の祝福", "Still Water Balm", "靜水的祝福"),
    SwiftWindsBalm("疾風の祝福", "Swift Winds Balm", "疾風的祝福"),
    Vengeance("復讐", "Vengeance", "復仇"),

    ArmoredBlow("金剛の一撃", "Armored Blow", "金剛一擊"),
    AtkDef("攻撃守備", "Atk Def", "攻擊防守"),
    AtkDefBond("攻撃守備の絆", "Atk Def Bond", "攻擊防守的羈絆"),
    AtkRes("攻撃魔防", "Atk Res", "攻擊魔防"),
    AtkResBond("攻撃魔防の絆", "Atk Res Bond", "攻擊魔防的羈絆"),
    AtkSpd("攻撃速さ", "Atk Spd", "攻擊速度"),
    Attack("攻撃", "Attack", "攻擊"),
    BracingBlow("金剛明鏡の一撃", "Bracing Blow", "金剛明鏡擊"),
    BrazenAtkDef("攻撃守備の大覚醒3", "Brazen Atk Def", "攻撃防守大覺醒3"),
    BrazenAtkSpd("攻撃速さの大覚醒3", "Brazen Atk Spd", "攻撃速度大覺醒3"),
    CloseCounter("近距離反撃", "Close Counter", "近距離反擊"),
    CloseDef("近距離防御", "Close Def", "近距離防禦"),
    DartingBlow("飛燕の一撃", "Darting Blow", "飛燕一擊"),
    DeathBlow("鬼神の一撃", "Death Blow", "鬼神一擊"),
    Defense("守備", "Defense", "防守"),
    DefiantAtk("攻撃の覚醒", "Defiant Atk", "攻擊覺醒"),
    DefiantDef("守備の覚醒", "Defiant Def", "防守覺醒"),
    DefiantRes("魔防の覚醒", "Defiant Res", "魔防覺醒"),
    DefiantSpd("速さの覚醒", "Defiant Spd", "速度覺醒"),
    DistantCounter("遠距離反撃", "Distant Counter", "遠距離反擊"),
    DistantDef("遠距離防御", "Distant Def", "遠距離防禦"),
    EarthBoost("生命の大地", "Earth Boost", "生命的大地"),
    FierceStance("鬼神の構え", "Fierce Stance", "鬼神架勢"),
    FireBoost("生命の業火", "Fire Boost", "生命的業火"),
    FlashingBlade("柔剣", "Flashing Blade", "柔劍"),
    FortressDef("守備の城塞", "Fortress Def", "防守堡壘"),
    FortressRes("魔防の城塞", "Fortress Res", "魔防堡壘"),
    Furry("獅子奮迅", "Furry", "獅子奮迅"),
    GranisShield("グラニの盾", "Grani's Shield", "〇〇〇之盾"),
    HeavyBlade("剛剣", "Heavy Blade", "剛劍"),
    Hp("HP", "Hp", "HP"),
    HpDef("HP守備", "Hp Def", "HP防守"),
    HpRes("HP魔防", "Hp Res", "HP魔防"),
    HpSpd("HP速さ", "Hp Spd", "HP速度"),
    IotesShield("アイオテの盾", "Iote's Shield", "〇〇〇之盾"),
    LifeAndDeath("死線", "Life And Death", "死線"),
    MirrorStrike("鬼神明鏡の一撃", "Mirror Strike", "鬼神明鏡擊"),
    Resistance("魔防", "Resistance", "魔防"),
    SpdDef("速さ守備", "Spd Def", "速度防守"),
    SpdRes("速さ魔防", "Spd Res", "速度魔防"),
    Speed("速さ", "Speed", "速度"),
    SteadyBlow("飛燕金剛の一撃", "Steady Blow", "飛燕金剛擊"),
    SteadyBreath("金剛の呼吸", "Steady Breath", "金剛の呼吸*"),
    SteadyStance("金剛の構え", "Steady Stance", "金剛架勢"),
    SturdyBlow("鬼神金剛の一撃", "Sturdy Blow", "鬼神金剛一擊"),
    SvalinnShield("スヴェルの盾", "Svalinn Shield", "〇〇〇之盾"),
    SwiftSparrow("鬼神飛燕の一撃", "Swift Sparrow", "鬼神飛燕擊"),
    SwiftStrike("飛燕明鏡の一撃", "Swift Strike", "飛燕明鏡擊"),
    TriangleAdept("相性激化", "Triangle Adept", "強化互克"),
    WardingBlow("明鏡の一撃", "Warding Blow", "明鏡一撃"),
    WardingStance("明鏡の構え", "Warding Stance", "明鏡架勢"),
    WaterBoost("生命の静水", "Water Boost", "生命的靜水"),
    WindBoost("生命の疾風", "Wind Boost", "生命的疾風"),

    AxeBreaker("斧殺し", "Axe Breaker", "破斧者"),
    BTomeBreaker("青魔殺し", "B Tome Breaker", "破青魔者"),
    BeorcsBlessing("ベオクの加護", "Beorc's Blessing", "貝歐克的加護"),
    BlazeDance("業火の舞い", "Blaze Dance", "業火之舞"),
    BoldFighter("攻撃隊形", "Bold Fighter", "攻擊隊形"),
    BowBreaker("弓殺し", "Bow Breaker", "破弓者"),
    BrashAssault("差し違え", "Brash Assault", "同〇〇〇"),
    CancelAffinity("相性相殺", "Cancel Affinity", "抵鋿*互克"),
    CrusadersWard("聖騎士の加護", "Crusaders Ward", "聖騎士的加護"),
    DaggerBreaker("暗器殺し", "Dagger Breaker", "破暗器者"),
    DazzlingStaff("幻惑の杖", "Dazzling Staff", "幻惑の杖"),
    Desperation("攻め立て", "Desperation", "猛攻"),
    DragBack("引き込み", "Drag Back", "引誘"),
    EscapeRoute("離脱の行路", "Escape Route", "脫離路線"),
    FlierFormation("編隊飛行", "Flier Formation", "編隊飛行"),
    FollowUpRing("追撃のリング", "Follow Up Ring", "追撃戒指"),
    GTomeBreaker("緑魔殺し", "G Tome Breaker", "破緑魔者"),
    GaleDance("疾風の舞い", "Gale Dance", "疾風之舞"),
    GeyserDance("大地静水の舞い", "Geyser Dance", "大地靜水之舞"),
    Guard("キャンセル", "Guard", "取消"),
    HitAndRun("一撃離脱", "Hit And Run", "一擊即離"),
    KnockBack("叩き込み", "Knock Back", "擊退"),
    LanceBreaker("槍殺し", "Lance Breaker", "破槍者"),
    LiveForBounty("豊穣の喜び", "Live For Bounty", "豊饒之喜悅"),
    LiveForHonor("栄誉の喜び", "Live For Honor", "榮〇之喜悅"),
    LiveToServe("ご奉仕の喜び", "Live To Serve", "奉〇〇〇〇"),
    Lunge("切り込み", "Lunge", "衝敵斬"),
    Obstruct("進軍阻止", "Obstruct", "阻止進軍"),
    Pass("すり抜け", "Pass", "穿越"),
    PoisonStrike("蛇毒", "Poison Strike", "蛇毒"),
    QuickRiposte("切り返し", "Quick Riposte", "回擊"),
    RTomeBreaker("赤魔殺し", "R Tome Breaker", "破赤魔者"),
    RecoverRing("リカバーリング", "Recover Ring", "聖癒戒指"),
    Renewal("回復", "Renewal", "回復"),
    SacaesBlessing("サカの加護", "Sacae's Blessing", "塞迦的加護"),
    SealAtk("攻撃封じ", "Seal Atk", "攻擊封印"),
    SealAtkDef("攻撃守備封じ", "Seal Atk Def", "攻擊防守封印"),
    SealAtkSpd("攻撃速さ封じ", "Seal Atk Spd", "攻擊速度封印"),
    SealDef("守備封じ", "Seal Def", "防守封印"),
    SealRes("魔防封じ", "Seal Res", "魔防封印"),
    SealSpd("速さ封じ", "Seal Spd", "速度封印"),
    ShieldPulse("盾の鼓動", "Shield Pulse", "盾之躍動"),
    SwordBreaker("剣殺し", "Sword Breaker", "破劍者"),
    TorrentDance("静水の舞い", "Torrent Dance", "靜水之舞"),
    Vantage("待ち伏せ", "Vantage", "埋伏"),
    VengefulFighter("迎撃隊形", "Vengeful Fighter", "迎撃隊形"),
    WaryFighter("守備隊形", "Wary Fighter", "防守隊形"),
    Watersweep("水薙ぎ", "Watersweep", "水薙"),
    Windsweep("風薙ぎ", "Windsweep", "疾風横掃"),
    WingsOfMercy("救援の行路", "Wings of Mercy", "救援路線"),
    Wrath("怒り", "Wrath", "怒り"),
    WrathfulStaff("神罰の杖", "Wrathful Staff", "神罰之杖"),

    ArmorMarch("重装の行軍", "Armor March", "重装備行軍"),
    AtkPloy("攻撃の謀策", "Attack Ploy", "攻擊謀策"),
    AtkSmoke("攻撃の紫煙", "Attack Smoke", "攻擊紫煙"),
    AtkTactic("攻撃の指揮", "Atk Tactic", "攻擊指揮"),
    AxeExperience("斧の経験", "Axe Experience", "斧之經驗"),
    AxeValor("斧の技量", "Axe Valor", "斧之技巧"),
    BTomeExperience("青魔の経験", "B Tome Experience", "青魔之經驗"),
    BTomeValor("青魔の技量", "B Tome Valor", "青魔之技巧"),
    BowExperience("弓の経験", "Bow Experience", "弓之經驗"),
    BreathOfLife("生の息吹", "Breath of Life", "生命氣息"),
    DaggerValor("暗器の技量", "Dagger Valor", "暗器之技巧"),
    DefPloy("守備の謀策", "Def Ploy", "防守謀策"),
    DefTactic("守備の指揮", "Def Tactic", "防守の指揮"),
    DriveAtk("攻撃の大紋章", "Drive Atk", "攻擊大紋章"),
    DriveDef("守備の大紋章", "Drive Def", "防守大紋章"),
    DriveRes("魔防の大紋章", "Drive Res", "魔防大紋章"),
    DriveSpd("速さの大紋章", "Drive Spd", "速度大紋章"),
    FortifyArmor("重盾の鼓舞", "Fortify Armor", "重盾鼓舞"),
    FortifyCavalry("騎盾の鼓舞", "Fortify Cavalry", "騎盾鼓舞"),
    FortifyDef("守備の鼓舞", "Fortify Def", "防守鼓舞"),
    FortifyDragons("竜盾の鼓舞", "Fortify Dragons", "竜盾鼓舞"),
    FortifyFliers("飛盾の鼓舞", "Fortify Fliers", "飛盾鼓舞"),
    FortifyRes("魔防の鼓舞", "Fortify Res", "魔防鼓舞"),
    GTomeValor("緑魔の技量", "G Tome Valor", "緑魔之技巧"),
    GoadArmor("重刃の紋章", "Goad Armor", "重刃紋章"),
    GoadCavalry("騎刃の紋章", "Goad Cavalry", "騎刃紋章"),
    GoadFliers("飛刃の紋章", "Goad Fliers", "飛刃紋章"),
    Guidance("空からの先導", "Guidance", "空中前導"),
    HoneArmor("重刃の鼓舞", "Hone Armor", "重刃鼓舞"),
    HoneAtk("攻撃の鼓舞", "Hone Atk", "攻擊鼓舞"),
    HoneCavalry("騎刃の鼓舞", "Hone Cavalry", "騎刃鼓舞"),
    HoneFliers("飛刃の鼓舞", "Hone Fliers", "飛刃鼓舞"),
    HoneSpd("速さの鼓舞", "Hone Spd", "速度鼓舞"),
    InfantryPulse("歩行の鼓動", "Infantry Pulse", "歩行躍動"),
    LanceValor("槍の技量", "LanceValor", "槍之技巧"),
    PanicPloy("恐慌の奇策", "Panic Ploy", "恐慌奇策"),
    RTomeValor("赤魔の技量", "R Tome Valor", "赤魔之技巧"),
    ResPloy("魔防の謀策", "Res Ploy", "魔防謀策"),
    SavageBlow("死の吐息", "Savage Blow", "死亡氣息"),
    SpdPloy("速さの謀策", "Spd Ploy", "速度謀策"),
    SpdSmoke("速さの紫煙", "Spd Smoke", "速度紫煙"),
    SpurAtk("攻撃の紋章", "Spur Atk", "攻擊紋章"),
    SpurDef("守備の紋章", "Spur Def", "防守紋章"),
    SpurDefRes("守備魔防の紋章", "Spur Def Res", "防守魔防紋章"),
    SpurRes("魔防の紋章", "Spur Res", "魔防紋章"),
    SpurSpd("速さの紋章", "Spur Spd", "速度紋章"),
    SpurSpdDef("速さ守備の紋章", "Spur Spd Def", "速度防守紋章"),
    SwordExperience("剣の経験", "Sword Experience", "劍之經驗"),
    SwordValor("剣の技量", "Sword Valor", "劍之技量"),
    ThreatenAtk("攻撃の威嚇", "Threaten Atk", "攻擊威嚇"),
    ThreatenDef("守備の威嚇", "Threaten Def", "防守威嚇"),
    ThreatenRes("魔防の威嚇", "Threaten Res", "魔防威嚇"),
    ThreatenSpd("速さの威嚇", "Threaten Spd", "速度威嚇"),
    WardArmor("重盾の紋章", "Ward Armor", "重盾紋章"),
    WardCavalry("騎盾の紋章", "Ward Cavalry", "騎盾紋章"),
    WardFliers("飛盾の紋章", "Ward Fliers", "飛盾紋章"),

    SquadAceA("第1迷宮の覇者", "Squad Ace A", "第1迷宮的霸王"),
    SquadAceB("第2迷宮の覇者", "Squad Ace B", "第2迷宮的霸王"),
    SquadAceC("第3迷宮の覇者", "Squad Ace C", "第3迷宮的霸王"),
    SquadAceD("第4迷宮の覇者", "Squad Ace D", "第4迷宮的霸王"),
    HardyBearing("不動の姿勢", "Hardy Bearing", "不動如山"),
    PhantomSpeed("速さの虚勢", "Phantom Speed", "速度誇飾"),
    QuickenedPulse("奥義の鼓動", "Quickened Pulse", "奥義之躍動"),
    DeflectMagic("連撃防御・魔", "Deflect Magic", "連擊防御・魔"),
    DeflectMelee("連撃防御・剣槍斧", "Deflect Melee", "連擊防禦・剣槍斧"),
    DeflectMissile("連撃防御・弓暗器", "Deflect Missile", "連擊防禦・弓暗器"),

    Range1Atk("攻撃(近)", "Atk(melee)", "攻擊(近)"),
    Range1Spd("速さ(近)", "Spd(melee)", "速度(近)"),
    Range1Def("守備(近)", "Def(melee)", "防守(近)"),
    Range1Res("魔防(近)", "Res(melee)", "魔防(近)"),

    Range2Atk("攻撃(遠)", "Atk(Ranged)", "攻擊(遠)"),
    Range2Spd("速さ(遠)", "Spd(Ranged)", "速度(遠)"),
    Range2Def("守備(遠)", "Def(Ranged)", "防守(遠)"),
    Range2Res("魔防(遠)", "Res(Ranged)", "魔防(遠)"),
    Pursuit("追撃", "Pursuit", "追撃"),
    SpecialDamage("奥義ダメージ＋10", "SpecialDamage", "奥義傷害+10"),
    Follow("追従", "Follow", "可移動至周圍2格"),
    MagicSuppression("魔法は反撃不能", "MagicSuppression", "魔法無法反擊"),
    Nullify("特効対象の強化の+を無効にする", "Nullifies foe's bonuses", "抵鋿敵人的＋強化狀態"),

    スリーズ("スリーズ","Gunnthrá","斯利茲"),
    アクア__正月_("アクア（正月）","Azura (Happy New Year!)","阿庫婭 (Happy New Year!)"),
    カミラ__正月_("カミラ（正月）","Camilla (Happy New Year!)","卡美拉 (Happy New Year!)"),
    タクミ__正月_("タクミ（正月）","Takumi (Happy New Year!)","拓海 (Happy New Year!)"),
    カムイ__正月_("カムイ（正月）","Corrin (Happy New Year!)","神威 (Happy New Year!)"),

    Blizzard("ブリザード","Blizzard","暴風雪"),
    ChillingSeal("冰の封印","Chilling Seal",""),
    EarthDance("大地の舞","EarthDance","大地之舞"),
    SpdDefBond("速さ守備の絆","Spd Def Bond","速度防守的羈絆"),
    Hagoita("羽子板","Hagoita","Hagoita"),
    Hagoita2("羽子板＋","Hagoita+","Hagoita+"),
    Kadomatsu("門松","Kadomatsu","Kadomatsu"),
    Kadomatsu2("門松＋","Kadomatsu+","Kadomatsu+"),
    KagamiMochi("鏡餅","Kagami Mochi","Kagami Mochi"),
    KagamiMochi2("鏡餅＋","Kagami Mochi+","Kagami Mochi+"),





    ;

    fun localeName(l: Locale): String =
            when (l) {
                Locale.JAPANESE -> jp
                Locale.TAIWAN -> tw
                else -> us
            }

    override fun toString(): String {
        return jp
    }
}