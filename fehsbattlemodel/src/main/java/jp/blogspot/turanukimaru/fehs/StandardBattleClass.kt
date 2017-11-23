package jp.blogspot.turanukimaru.fehs

/**
 * Created by turanukimaru on 2017/11/01.
 */
object StandardBattleClass {
    //initブロックと同じ優先順位、つまりinitの前に書かないと作成されない
    private val ITEMS = ArrayList<BattleClass>()
    private val ITEMMAP = HashMap<String, BattleClass>()
    fun get(id: String): BattleClass? = ITEMMAP[id]?.clone()
    fun get(id: Int): BattleClass? = ITEMS[id].clone()
    fun containsKey(id: String): Boolean = ITEMMAP.containsKey(id)
    fun allItems(): MutableList<BattleClass> = ITEMS.fold(mutableListOf(), { list, e -> list.add(e);list })

    init {
        creates()
    }

    fun creates() {
        // Add a person
        createItem("アイク", 1, WeaponType.SWORD, MoveType.INFANTRY, "Ike", 5, 18, 9, 7, 8, 5, 7, 8, 7, 7, 2,
                Weapons.Ragnell, Skill.NONE, Specials.Aether, Skills.HeavyBlade.lv(3), Skills.SwordBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("アイラ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Ayra", 4, 19, 7, 11, 7, 4, 6, 8, 8, 7, 4,
                Weapons.AyrasBlade, Skill.NONE, Specials.RegnalAstra, Skills.SwiftSparrow.lv(2), Skills.Desperation.lv(3), Skill.NONE, Skill.NONE)
        createItem("アーダン", 1, WeaponType.SWORD, MoveType.ARMORED, "Arden", 4, 25, 10, 3, 13, 3, 12, 8, 2, 9, 2,
                Weapons.BraveSword2, Skill.NONE, Specials.Pavise, Skills.FollowUpRing, Skills.DriveDef.lv(2), Skill.NONE)
        createItem("アテナ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Athena", 4, 17, 7, 10, 8, 5, 5, 7, 9, 5, 5,
                Weapons.WaoDao2, Skill.NONE, Specials.Moonbow, Skills.SturdyBlow.lv(2), Skill.NONE, Skills.SwordExperience.lv(3), Skill.NONE)
        createItem("アルフォンス", 1, WeaponType.SWORD, MoveType.INFANTRY, "Alfonse", 2, 19, 9, 6, 8, 5, 7, 8, 5, 7, 4,
                Weapons.Folkvangr, Skill.NONE, Specials.Sol, Skills.DeathBlow.lv(3), Skill.NONE, Skills.SpurAtk.lv(3), Skill.NONE)
        createItem("アルム", 1, WeaponType.SWORD, MoveType.INFANTRY, "Alm", 5, 21, 9, 6, 6, 5, 7, 7, 7, 6, 4,
                Weapons.Falchion, Skill.NONE, Specials.DragonGaze, Skills.Attack.lv(3), Skills.Windsweep.lv(3), Skill.NONE, Skill.NONE)
        createItem("エイリーク", 1, WeaponType.SWORD, MoveType.INFANTRY, "Eirika", 4, 18, 7, 9, 7, 6, 7, 5, 8, 5, 6,
                Weapons.Sieglinde, Assists.Pivot, Skill.NONE, Skill.NONE, Skills.DragBack, Skills.HoneSpd.lv(3), Skill.NONE)
        createItem("エリウッド", 1, WeaponType.SWORD, MoveType.CAVALRY, "Eliwood", 3, 17, 7, 8, 6, 8, 6, 7, 6, 4, 7,
                Weapons.Durandal, Skill.NONE, Specials.SacredCowl, Skill.NONE, Skills.AxeBreaker.lv(3), Skills.WardCavalry, Skill.NONE)
        createItem("エリンシア", 1, WeaponType.SWORD, MoveType.FLIER, "Elincia", 5, 16, 8, 10, 5, 8, 5, 8, 8, 5, 5,
                Weapons.Amiti, Assists.ArdentSacrifice, Skill.NONE, Skills.DeathBlow.lv(3), Skills.FlierFormation.lv(3), Skill.NONE)
        createItem("エルトシャン", 1, WeaponType.SWORD, MoveType.CAVALRY, "Eldigan", 5, 19, 8, 5, 8, 6, 8, 7, 5, 8, 2,
                Weapons.Mystletainn, Skill.NONE, Specials.GrowingLight, Skills.Furry.lv(3), Skills.Lunge, Skill.NONE, Skill.NONE)
        createItem("オグマ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Ogma", 4, 21, 7, 10, 6, 3, 8, 9, 7, 6, 1,
                Weapons.BraveSword2, Skill.NONE, Specials.Noontime, Skills.DefiantAtk.lv(3), Skill.NONE, Skills.SpurAtk.lv(3), Skill.NONE)
        createItem("オリヴィエ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Olivia", 3, 17, 6, 7, 5, 4, 5, 6, 8, 6, 6,
                Weapons.SilverSword2, Assists.Dance, Skill.NONE, Skill.NONE, Skills.KnockBack, Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("カイン", 1, WeaponType.SWORD, MoveType.CAVALRY, "Cain", 4, 18, 8, 6, 8, 6, 7, 7, 8, 5, 3,
                Weapons.BraveSword2, Skill.NONE, Specials.Escutcheon, Skill.NONE, Skills.WingsOfMercy.lv(3), Skills.ThreatenAtk.lv(3), Skill.NONE)
        createItem("カザハナ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Hana", 3, 18, 9, 10, 6, 4, 5, 8, 8, 4, 6,
                Weapons.ArmorSlayer2, Assists.RallyAttack, Skill.NONE, Skills.LifeAndDeath.lv(3), Skills.Obstruct.lv(3), Skill.NONE, Skill.NONE)
        createItem("カムイ（男）", 1, WeaponType.SWORD, MoveType.INFANTRY, "Corrin(M)", 4, 20, 8, 8, 6, 5, 6, 7, 7, 6, 5,
                Weapons.Yato, Skill.NONE, Specials.DragonFang, Skills.Defense.lv(3), Skills.Obstruct.lv(3), Skill.NONE, Skill.NONE)
        createItem("カレル", 1, WeaponType.SWORD, MoveType.INFANTRY, "Karel", 5, 19, 8, 9, 6, 5, 9, 6, 8, 5, 3,
                Weapons.WaoDao2, Skill.NONE, Specials.Reprisal, Skills.DefiantAtk.lv(3), Skills.Desperation.lv(3), Skill.NONE, Skill.NONE)
        createItem("グレイ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Gray", 5, 17, 7, 6, 6, 3, 8, 9, 8, 7, 5,
                Weapons.Zanbato2, Assists.Swap, Skill.NONE, Skills.WindBoost.lv(3), Skill.NONE, Skills.SwordValor.lv(3), Skill.NONE)
        createItem("クロム", 1, WeaponType.SWORD, MoveType.INFANTRY, "Chrom", 4, 21, 9, 6, 7, 4, 8, 9, 5, 7, 2,
                Weapons.Falchion, Skill.NONE, Specials.Aether, Skills.DefiantDef.lv(3), Skill.NONE, Skills.SpurDef.lv(3), Skill.NONE)
        createItem("シーダ", 1, WeaponType.SWORD, MoveType.FLIER, "Caeda", 4, 17, 6, 9, 5, 10, 5, 5, 9, 5, 7,
                Weapons.ArmorSlayer2, Assists.RallySpeed, Skill.NONE, Skills.DartingBlow.lv(3), Skill.NONE, Skills.FortifyFliers, Skill.NONE)
        createItem("シグルド", 1, WeaponType.SWORD, MoveType.CAVALRY, "Sigurd", 5, 19, 9, 8, 6, 4, 6, 8, 7, 9, 2,
                Weapons.DivineTyrfing, Skill.NONE, Specials.Miracle, Skills.CloseDef.lv(3), Skills.CrusadersWard, Skills.SpdSmoke.lv(3), Skill.NONE)
        createItem("漆黒の騎士", 1, WeaponType.SWORD, MoveType.ARMORED, "Black Knight", 4, 22, 10, 8, 9, 5, 8, 7, 8, 8, 2,
                Weapons.Alondite, Skill.NONE, Specials.BlackLuna, Skills.SteadyStance.lv(3), Skills.WingsOfMercy.lv(3), Skill.NONE, Skill.NONE)
        createItem("セーバー", 1, WeaponType.SWORD, MoveType.INFANTRY, "Saber", 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Weapons.SlayingEdge2, Skill.NONE, Specials.Aegis, Skills.HpSpd.lv(2), Skills.ShieldPulse.lv(3), Skill.NONE, Skill.NONE)
        createItem("ゼト", 1, WeaponType.SWORD, MoveType.CAVALRY, "Seth", 5, 18, 8, 7, 8, 5, 5, 7, 7, 7, 4,
                Weapons.RubySword2, Assists.Swap, Skill.NONE, Skills.FortressDef.lv(3), Skills.SealAtkDef.lv(2), Skill.NONE, Skill.NONE)
        createItem("ゼフィール", 1, WeaponType.SWORD, MoveType.ARMORED, "Zephiel", 3, 25, 9, 3, 12, 5, 10, 8, 2, 8, 5,
                Weapons.Eckesachs, Skill.NONE, Specials.Reprisal, Skills.LifeAndDeath.lv(3), Skills.WaryFighter.lv(3), Skill.NONE, Skill.NONE)
        createItem("セリス", 1, WeaponType.SWORD, MoveType.INFANTRY, "Seliph", 4, 19, 8, 7, 8, 5, 9, 8, 4, 6, 4,
                Weapons.Tyrfing, Assists.RallySpeed, Skill.NONE, Skills.Hp.lv(3), Skills.BrashAssault.lv(3), Skill.NONE, Skill.NONE)
        createItem("ソール", 1, WeaponType.SWORD, MoveType.CAVALRY, "Stahl", 3, 19, 7, 7, 8, 5, 8, 7, 5, 6, 4,
                Weapons.RubySword2, Assists.Swap, Skill.NONE, Skills.Defense.lv(3), Skills.Obstruct.lv(3), Skill.NONE, Skill.NONE)
        createItem("ドーガ", 1, WeaponType.SWORD, MoveType.ARMORED, "Draug", 2, 24, 8, 6, 13, 3, 8, 6, 8, 8, 3,
                Weapons.BraveSword2, Skill.NONE, Specials.Pavise, Skill.NONE, Skills.Lunge, Skills.WardArmor, Skill.NONE)
        createItem("ナバール", 1, WeaponType.SWORD, MoveType.INFANTRY, "Navarre", 3, 18, 7, 11, 6, 5, 7, 7, 8, 4, 5,
                Weapons.KillingEdge2, Skill.NONE, Specials.BlazingWind, Skill.NONE, Skills.Desperation.lv(3), Skills.ThreatenSpd.lv(3), Skill.NONE)
        createItem("パオラ", 1, WeaponType.SWORD, MoveType.FLIER, "Palla", 3, 18, 7, 9, 6, 7, 7, 7, 6, 6, 5,
                Weapons.RubySword2, Skill.NONE, Specials.Moonbow, Skill.NONE, Skills.WingsOfMercy.lv(3), Skills.GoadFliers, Skill.NONE)
        createItem("ヒナタ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Hinata", 3, 21, 8, 5, 10, 3, 8, 7, 5, 8, 3,
                Weapons.RubySword2, Skill.NONE, Specials.Pavise, Skills.Furry.lv(3), Skills.BrashAssault.lv(3), Skill.NONE, Skill.NONE)
        createItem("フィル", 1, WeaponType.SWORD, MoveType.INFANTRY, "Fir", 3, 19, 6, 10, 5, 7, 6, 5, 8, 5, 7,
                Weapons.KillingEdge2, Skill.NONE, Specials.Glacies, Skills.Speed.lv(3), Skills.Pass.lv(3), Skill.NONE, Skill.NONE)
        createItem("マルス", 1, WeaponType.SWORD, MoveType.INFANTRY, "Marth", 4, 19, 7, 8, 7, 6, 6, 7, 8, 6, 4,
                Weapons.Falchion, Assists.Pivot, Skill.NONE, Skill.NONE, Skills.EscapeRoute.lv(3), Skills.SpurSpd.lv(3), Skill.NONE)
        createItem("マルス（仮面）", 1, WeaponType.SWORD, MoveType.INFANTRY, "Marth (Masked)", 4, 19, 8, 10, 6, 4, 7, 8, 8, 5, 3,
                Weapons.Falchion, Skill.NONE, Skill.NONE, Skill.NONE, Skill.NONE, Skill.NONE, Skill.NONE)
        createItem("マークス", 1, WeaponType.SWORD, MoveType.CAVALRY, "Xander", 3, 20, 8, 5, 9, 4, 7, 7, 5, 9, 2,
                Weapons.Siegfried, Skill.NONE, Specials.BlazingLight, Skills.ArmoredBlow.lv(3), Skill.NONE, Skills.SpurDef.lv(3), Skill.NONE)
        createItem("ラズワルド", 1, WeaponType.SWORD, MoveType.INFANTRY, "Laslow", 3, 20, 9, 7, 6, 5, 7, 8, 5, 7, 4,
                Weapons.SilverSword2, Skill.NONE, Specials.Noontime, Skill.NONE, Skills.AxeBreaker.lv(3), Skills.HoneSpd.lv(3), Skill.NONE)
        createItem("リョウマ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Ryoma", 5, 19, 8, 11, 5, 4, 6, 8, 7, 6, 4,
                Weapons.Raijinto, Skill.NONE, Specials.Astra, Skills.DefiantAtk.lv(3), Skill.NONE, Skills.HoneSpd.lv(3), Skill.NONE)
        createItem("リン", 1, WeaponType.SWORD, MoveType.INFANTRY, "Lyn", 5, 18, 6, 11, 7, 5, 5, 6, 8, 5, 7,
                Weapons.SolKatti, Skill.NONE, Specials.Galeforce, Skills.DefiantAtk.lv(3), Skill.NONE, Skills.SpurSpd.lv(3), Skill.NONE)
        createItem("ルーク", 1, WeaponType.SWORD, MoveType.CAVALRY, "Luke", 5, 19, 8, 6, 8, 5, 7, 8, 6, 6, 3,
                Weapons.BraveSword2, Skill.NONE, Specials.Boonfire, Skills.FireBoost.lv(3), Skill.NONE, Skills.PanicPloy.lv(3), Skill.NONE)
        createItem("ルーナ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Selena", 3, 18, 6, 9, 8, 6, 5, 5, 8, 7, 6,
                Weapons.ArmorSlayer2, Assists.Reposition, Skill.NONE, Skills.TriangleAdept.lv(3), Skill.NONE, Skills.ThreatenSpd.lv(3), Skill.NONE)
        createItem("ルキナ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Lucina", 5, 19, 8, 10, 6, 4, 7, 8, 8, 5, 3,
                Weapons.Falchion, Skill.NONE, Specials.Aether, Skills.DefiantSpd.lv(3), Skill.NONE, Skills.SpurAtk.lv(3), Skill.NONE)
        createItem("ロイ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Roy", 4, 20, 8, 9, 6, 4, 7, 6, 6, 5, 7,
                Weapons.BindingBlade, Assists.Shove, Skill.NONE, Skills.TriangleAdept.lv(3), Skills.SealDef.lv(3), Skill.NONE, Skill.NONE)
        createItem("ロイ（総選挙）", 1, WeaponType.SWORD, MoveType.CAVALRY, "Roy (Brave Heroes)", 5, 16, 8, 8, 7, 7, 6, 7, 8, 5, 4,
                Weapons.BlazingDurandal, Skill.NONE, Specials.Galeforce, Skills.SteadyBlow.lv(2), Skills.Desperation.lv(3), Skill.NONE, Skill.NONE)
        createItem("ロイド", 1, WeaponType.SWORD, MoveType.INFANTRY, "Lloyd", 3, 17, 8, 9, 5, 8, 7, 7, 8, 3, 6,
                Weapons.RegalBlade, Skill.NONE, Specials.Iceberg, Skill.NONE, Skills.Pass.lv(3), Skills.ThreatenAtk.lv(3), Skill.NONE)
        createItem("ロビン", 1, WeaponType.SWORD, MoveType.INFANTRY, "Tobin", 4, 18, 7, 5, 5, 4, 9, 7, 6, 9, 6,
                Weapons.ArmorSlayer2, Assists.Pivot, Skill.NONE, Skills.Attack.lv(3), Skills.SealSpd.lv(3), Skill.NONE, Skill.NONE)
        createItem("ロンクー", 1, WeaponType.SWORD, MoveType.INFANTRY, "Lonqu", 3, 19, 7, 11, 5, 5, 8, 6, 9, 4, 4,
                Weapons.KillingEdge2, Skill.NONE, Specials.Glimmer, Skills.Speed.lv(3), Skills.Vantage.lv(3), Skill.NONE, Skill.NONE)
        createItem("アルヴィス", 1, WeaponType.RTOME, MoveType.INFANTRY, "Arvis", 3, 18, 8, 7, 4, 7, 3, 8, 7, 2, 8,
                Weapons.Valflame, Skill.NONE, Specials.GrowingFlame, Skill.NONE, Skills.RecoverRing, Skills.DefPloy.lv(3), Skill.NONE)
        createItem("カタリナ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Katarina", 5, 17, 6, 8, 5, 8, 4, 8, 8, 1, 7,
                Weapons.Rauorowl2, Skill.NONE, Specials.Glacies, Skills.SwiftSparrow.lv(2), Skill.NONE, Skills.AtkPloy.lv(3), Skill.NONE)
        createItem("サーリャ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Tharja", 4, 17, 8, 8, 6, 5, 6, 7, 8, 4, 3,
                Weapons.Rauorblade2, Skill.NONE, Specials.Vengeance, Skills.DartingBlow.lv(3), Skill.NONE, Skills.SpurRes.lv(3), Skill.NONE)
        createItem("サナキ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Sanaki", 5, 16, 9, 7, 4, 8, 4, 9, 5, 2, 8,
                Weapons.Cymbeline, Assists.HarshCommand, Skill.NONE, Skills.TriangleAdept.lv(3), Skill.NONE, Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("セリカ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Celica", 5, 17, 8, 7, 5, 7, 6, 7, 8, 4, 3,
                Weapons.Ragnarok, Skill.NONE, Specials.BlazingLight, Skills.DistantDef.lv(3), Skill.NONE, Skills.SpurDef.lv(3), Skill.NONE)
        createItem("ソフィーヤ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Sophia", 3, 18, 9, 4, 6, 7, 6, 7, 3, 6, 6,
                Weapons.Fenrir2, Skill.NONE, Specials.DragonFang, Skills.WardingBlow.lv(3), Skill.NONE, Skills.FortifyRes.lv(3), Skill.NONE)
        createItem("ヘンリー", 1, WeaponType.RTOME, MoveType.INFANTRY, "Henry", 3, 19, 6, 5, 8, 6, 8, 4, 4, 7, 5,
                Weapons.Rauorraven2, Skill.NONE, Specials.Ignis, Skills.DefiantDef.lv(3), Skills.GTomeBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("リリーナ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Lilina", 4, 16, 9, 6, 4, 9, 5, 9, 5, 3, 6,
                Weapons.Bolganone2, Skill.NONE, Specials.GrowingFlame, Skills.Attack.lv(3), Skill.NONE, Skills.SpurAtk.lv(3), Skill.NONE)
        createItem("レイ", 1, WeaponType.RTOME, MoveType.INFANTRY, "Raigh", 3, 17, 8, 7, 5, 7, 5, 7, 6, 4, 6,
                Weapons.Rauorwolf2, Assists.RallyAttack, Skill.NONE, Skills.Hp.lv(3), Skills.SealRes.lv(3), Skill.NONE, Skill.NONE)
        createItem("レオン", 1, WeaponType.RTOME, MoveType.CAVALRY, "Leo", 5, 17, 7, 5, 6, 8, 6, 6, 4, 5, 6,
                Weapons.Brynhildr, Skill.NONE, Specials.BlazingLight, Skill.NONE, Skills.QuickRiposte.lv(3), Skills.SavageBlow.lv(3), Skill.NONE)
        createItem("レオン（夏）", 1, WeaponType.RTOME, MoveType.INFANTRY, "Leo (Summer)", 5, 18, 8, 5, 6, 7, 5, 7, 6, 1, 9,
                Weapons.TomatoTome2, Skill.NONE, Specials.Iceberg, Skill.NONE, Skills.SealRes.lv(3), Skills.AtkPloy.lv(3), Skill.NONE)
        createItem("チキ（大人）", 1, WeaponType.BREATH, MoveType.DRAGON, "Tiki(A)", 3, 18, 7, 6, 9, 7, 6, 9, 4, 8, 4,
                Weapons.LightningBreath2, Skill.NONE, Specials.Boonfire, Skills.DefiantAtk.lv(3), Skill.NONE, Skills.SpurRes.lv(3), Skill.NONE)
        createItem("チキ（幼）", 1, WeaponType.BREATH, MoveType.DRAGON, "Tiki(Y)", 5, 15, 5, 4, 8, 7, 8, 8, 8, 7, 6,
                Weapons.Flametongue2, Skill.NONE, Specials.GrowingFlame, Skills.ArmoredBlow.lv(3), Skill.NONE, Skills.BreathOfLife.lv(3), Skill.NONE)
        createItem("アクア", 2, WeaponType.LANCE, MoveType.INFANTRY, "Azura", 5, 17, 5, 7, 4, 6, 5, 8, 8, 4, 6,
                Weapons.SapphireLance2, Assists.Sing, Skill.NONE, Skills.Speed.lv(3), Skill.NONE, Skills.FortifyRes.lv(3), Skill.NONE)
        createItem("アベル", 2, WeaponType.LANCE, MoveType.CAVALRY, "Abel", 4, 17, 7, 8, 8, 6, 6, 8, 7, 4, 5,
                Weapons.BraveLance2, Skill.NONE, Specials.Aegis, Skills.Hp.lv(3), Skills.SwordBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("ヴァルター", 2, WeaponType.LANCE, MoveType.FLIER, "Valter", 3, 18, 8, 9, 8, 4, 7, 7, 6, 8, 3,
                Weapons.CursedLance, Skill.NONE, Specials.Luna, Skills.DartingBlow.lv(3), Skill.NONE, Skills.PanicPloy.lv(3), Skill.NONE)
        createItem("ウェンディ", 2, WeaponType.LANCE, MoveType.ARMORED, "Gwendolyn", 3, 23, 8, 5, 12, 6, 8, 6, 5, 8, 6,
                Weapons.KillerLance2, Skill.NONE, Specials.Escutcheon, Skill.NONE, Skills.DragBack, Skills.HoneArmor, Skill.NONE)
        createItem("エスト", 2, WeaponType.LANCE, MoveType.FLIER, "Est", 3, 17, 9, 8, 5, 8, 5, 8, 6, 5, 7,
                Weapons.HeavySpear2, Assists.Shove, Skill.NONE, Skills.DefiantRes.lv(3), Skills.SealSpd.lv(3), Skill.NONE, Skill.NONE)
        createItem("エフラム", 2, WeaponType.LANCE, MoveType.INFANTRY, "Ephraim", 5, 19, 9, 6, 8, 5, 8, 8, 5, 7, 3,
                Weapons.Siegmund, Skill.NONE, Specials.Moonbow, Skill.NONE, Skills.SealDef.lv(3), Skills.ThreatenDef.lv(3), Skill.NONE)
        createItem("エルフィ", 2, WeaponType.LANCE, MoveType.ARMORED, "Effie", 4, 22, 12, 5, 11, 4, 9, 9, 4, 6, 5,
                Weapons.SilverLance2, Assists.Smite, Skill.NONE, Skills.DeathBlow.lv(3), Skills.WaryFighter.lv(3), Skill.NONE, Skill.NONE)
        createItem("オスカー", 2, WeaponType.LANCE, MoveType.CAVALRY, "Oscar", 5, 18, 7, 8, 7, 6, 6, 8, 8, 5, 3,
                Weapons.SapphireLance2, Assists.RallySpdDef, Skill.NONE, Skill.NONE, Skills.LanceBreaker.lv(3), Skills.SpurSpdDef.lv(2), Skill.NONE)
        createItem("オボロ", 2, WeaponType.LANCE, MoveType.INFANTRY, "Oboro", 3, 18, 8, 7, 9, 5, 6, 7, 5, 8, 5,
                Weapons.HeavySpear2, Assists.RallyDefense, Skill.NONE, Skill.NONE, Skills.SealDef.lv(3), Skills.ThreatenRes.lv(3), Skill.NONE)
        createItem("カチュア", 2, WeaponType.LANCE, MoveType.FLIER, "Catria", 4, 17, 7, 10, 7, 6, 6, 7, 7, 6, 5,
                Weapons.KillerLance2, Skill.NONE, Specials.Luna, Skills.ArmoredBlow.lv(3), Skills.SealAtk.lv(3), Skill.NONE, Skill.NONE)
        createItem("カミュ", 2, WeaponType.LANCE, MoveType.CAVALRY, "Camus", 3, 18, 8, 9, 7, 4, 7, 7, 7, 7, 2,
                Weapons.Gradivus, Skill.NONE, Specials.GrowingThunder, Skills.GranisShield, Skill.NONE, Skills.GoadCavalry, Skill.NONE)
        createItem("カムイ（女）", 2, WeaponType.BREATH, MoveType.DRAGON, "Corrin(F)", 3, 19, 8, 6, 8, 6, 6, 5, 9, 8, 3,
                Weapons.DarkBreath2, Skill.NONE, Specials.DragonGaze, Skill.NONE, Skills.SealRes.lv(3), Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("クレア", 2, WeaponType.LANCE, MoveType.FLIER, "Clair", 5, 18, 7, 8, 5, 9, 5, 5, 9, 5, 7,
                Weapons.SilverLance2, Assists.HarshCommand, Skill.NONE, Skill.NONE, Skills.HitAndRun, Skills.SpurSpd.lv(3), Skill.NONE)
        createItem("クレーベ", 2, WeaponType.LANCE, MoveType.CAVALRY, "Clive", 4, 19, 9, 6, 8, 4, 8, 7, 5, 7, 3,
                Weapons.SilverLance2, Skill.NONE, Specials.Escutcheon, Skills.Defense.lv(3), Skills.HitAndRun, Skill.NONE, Skill.NONE)
        createItem("シーダ（花嫁）", 2, WeaponType.BTOME, MoveType.INFANTRY, "Caeda (Bride)", 5, 16, 7, 9, 4, 8, 4, 6, 9, 3, 6,
                Weapons.BlessedBouquet2, Skill.NONE, Specials.Iceberg, Skills.AtkRes.lv(2), Skill.NONE, Skills.HoneSpd.lv(3), Skill.NONE)
        createItem("ジェイガン", 2, WeaponType.LANCE, MoveType.CAVALRY, "Jagen", 3, 20, 8, 7, 8, 11, 4, 5, 4, 4, 7,
                Weapons.SilverLance2, Skill.NONE, Specials.Aegis, Skills.Furry.lv(3), Skill.NONE, Skills.FortifyCavalry, Skill.NONE)
        createItem("シャーロッテ（花嫁）", 2, WeaponType.LANCE, MoveType.INFANTRY, "Charlotte (Bride)", 5, 20, 10, 8, 5, 4, 8, 8, 7, 5, 3,
                Weapons.FirstBite2, Assists.Smite, Skill.NONE, Skills.WindBoost.lv(3), Skill.NONE, Skills.ThreatenAtk.lv(3), Skill.NONE)
        createItem("シャニー", 2, WeaponType.LANCE, MoveType.FLIER, "Shanna", 3, 17, 8, 9, 6, 7, 6, 6, 8, 5, 6,
                Weapons.KillerLance2, Skill.NONE, Specials.Iceberg, Skill.NONE, Skills.Desperation.lv(3), Skills.ThreatenSpd.lv(3), Skill.NONE)
        createItem("シャロン", 2, WeaponType.LANCE, MoveType.INFANTRY, "Sharena", 2, 19, 8, 8, 7, 5, 7, 7, 7, 6, 4,
                Weapons.Fensalir, Assists.RallyAttack, Skill.NONE, Skills.Speed.lv(3), Skill.NONE, Skills.FortifyDef.lv(3), Skill.NONE)
        createItem("ソワレ", 2, WeaponType.LANCE, MoveType.CAVALRY, "Sully", 3, 18, 7, 8, 7, 6, 7, 5, 8, 4, 6,
                Weapons.SapphireLance2, Assists.DrawBack, Skill.NONE, Skill.NONE, Skills.SwordBreaker.lv(3), Skills.SpurDef.lv(3), Skill.NONE)
        createItem("ターナ", 2, WeaponType.LANCE, MoveType.FLIER, "Tana", 5, 17, 8, 10, 6, 6, 5, 8, 8, 5, 5,
                Weapons.Vidofinir, Skill.NONE, Specials.Moonbow, Skills.SpdDef.lv(2), Skill.NONE, Skills.Guidance.lv(3), Skill.NONE)
        createItem("ツバキ", 2, WeaponType.LANCE, MoveType.FLIER, "Subaki", 3, 18, 6, 9, 9, 5, 6, 5, 8, 8, 4,
                Weapons.SapphireLance2, Assists.Swap, Skill.NONE, Skills.Resistance.lv(3), Skills.QuickRiposte.lv(3), Skill.NONE, Skill.NONE)
        createItem("ティアモ", 2, WeaponType.LANCE, MoveType.FLIER, "Cordelia", 4, 18, 9, 9, 5, 6, 6, 8, 8, 4, 5,
                Weapons.BraveLance2, Skill.NONE, Specials.Galeforce, Skills.TriangleAdept.lv(3), Skills.Pass.lv(3), Skill.NONE, Skill.NONE)
        createItem("ドニ", 2, WeaponType.LANCE, MoveType.INFANTRY, "Donnel", 3, 17, 7, 5, 6, 4, 8, 9, 7, 8, 5,
                Weapons.BraveLance2, Assists.ReciprocalAid, Skill.NONE, Skills.Hp.lv(3), Skills.DragBack, Skill.NONE, Skill.NONE)
        createItem("ネフェニー", 2, WeaponType.LANCE, MoveType.INFANTRY, "Nephenee", 5, 18, 7, 9, 8, 5, 5, 7, 8, 8, 3,
                Weapons.SlayingLance2, Skill.NONE, Specials.Moonbow, Skills.AtkSpd.lv(2), Skills.Wrath.lv(3), Skill.NONE, Skill.NONE)
        createItem("ピエリ", 2, WeaponType.LANCE, MoveType.CAVALRY, "Peri", 4, 16, 9, 9, 6, 6, 5, 7, 7, 4, 7,
                Weapons.KillerLance2, Skill.NONE, Specials.Glimmer, Skills.Resistance.lv(3), Skill.NONE, Skills.ThreatenDef.lv(3), Skill.NONE)
        createItem("ヒノカ", 2, WeaponType.LANCE, MoveType.FLIER, "Hinoka", 5, 19, 7, 8, 6, 7, 6, 9, 7, 5, 4,
                Weapons.BraveLance2, Skill.NONE, Specials.BlazingWind, Skills.DefiantDef.lv(3), Skill.NONE, Skills.HoneFliers, Skill.NONE)
        createItem("フロリーナ", 2, WeaponType.LANCE, MoveType.FLIER, "Florina", 3, 18, 7, 8, 6, 8, 7, 6, 5, 5, 8,
                Weapons.HeavySpear2, Assists.ArdentSacrifice, Skill.NONE, Skills.DartingBlow.lv(3), Skill.NONE, Skills.BreathOfLife.lv(3), Skill.NONE)
        createItem("ベルクト", 2, WeaponType.LANCE, MoveType.CAVALRY, "Berkut", 3, 19, 8, 5, 7, 7, 7, 8, 4, 7, 4,
                Weapons.BerkutsLance2, Skill.NONE, Specials.BlazingFlame, Skills.WaterBoost.lv(3), Skill.NONE, Skills.WardCavalry, Skill.NONE)
        createItem("マークス（春）", 2, WeaponType.LANCE, MoveType.CAVALRY, "Xander (Spring)", 5, 18, 6, 6, 9, 7, 6, 5, 6, 8, 5,
                Weapons.CarrotLance2, Assists.Swap, Skill.NONE, Skill.NONE, Skills.LiveForHonor, Skills.FortifyDef.lv(3), Skill.NONE)
        createItem("マチルダ", 2, WeaponType.LANCE, MoveType.CAVALRY, "Mathilda", 5, 16, 7, 8, 7, 8, 5, 6, 7, 4, 8,
                Weapons.Ridersbane2, Assists.RallyResistance, Skill.NONE, Skill.NONE, Skills.CancelAffinity.lv(3), Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("ルカ", 2, WeaponType.LANCE, MoveType.INFANTRY, "Lukas", 5, 19, 9, 5, 10, 4, 8, 8, 4, 9, 2,
                Weapons.KillerLance2, Skill.NONE, Specials.SacredCowl, Skills.FortressDef.lv(3), Skills.Obstruct.lv(3), Skill.NONE, Skill.NONE)
        createItem("ルキナ（春）", 2, WeaponType.BTOME, MoveType.INFANTRY, "Lucina (Spring)", 5, 16, 7, 10, 5, 6, 5, 6, 8, 4, 5,
                Weapons.BlueEgg2, Assists.RallySpeed, Skill.NONE, Skills.SwiftSparrow.lv(2), Skills.SealRes.lv(3), Skill.NONE, Skill.NONE)
        createItem("ルキナ（総選挙）", 2, WeaponType.LANCE, MoveType.INFANTRY, "Lucina (Brave Heroes)", 5, 17, 8, 10, 8, 4, 7, 8, 8, 5, 3,
                Weapons.Geirskogul, Skill.NONE, Specials.Aether, Skills.SturdyBlow.lv(2), Skill.NONE, Skills.DriveSpd.lv(2), Skill.NONE)
        createItem("ルフレ（夏）", 2, WeaponType.LANCE, MoveType.INFANTRY, "Robin(F) (Summer)", 5, 18, 8, 8, 6, 7, 4, 7, 8, 6, 6,
     Weapons.DeftHarpoon2,           Assists.Reposition, Skill.NONE, Skills.HpDef.lv(2), Skill.NONE, Skills.LanceValor.lv(3), Skill.NONE)
        createItem("ロディ", 2, WeaponType.LANCE, MoveType.CAVALRY, "Roderick", 5, 18, 7, 8, 6, 7, 6, 7, 8, 5, 4,
                Weapons.FiresweepLance2, Assists.RallyDefRes, Skill.NONE, Skill.NONE, Skills.DragBack, Skills.DriveDef.lv(2), Skill.NONE)
        createItem("ウルスラ", 2, WeaponType.BTOME, MoveType.CAVALRY, "Ursula", 3, 16, 7, 8, 4, 8, 5, 6, 7, 3, 6,
                Weapons.Blarwolf2, Skill.NONE, Specials.GrowingThunder, Skills.DeathBlow.lv(3), Skill.NONE, Skills.ThreatenRes.lv(3), Skill.NONE)
        createItem("オーディン", 2, WeaponType.BTOME, MoveType.INFANTRY, "Odin", 3, 19, 5, 8, 6, 6, 7, 4, 7, 5, 5,
                Weapons.Blarblade2, Skill.NONE, Specials.Moonbow, Skills.DefiantAtk.lv(3), Skills.RTomeBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("オルエン", 2, WeaponType.BTOME, MoveType.CAVALRY, "Olwen", 5, 17, 7, 8, 5, 6, 4, 5, 8, 3, 7,
                Weapons.DireThunder, Assists.Reposition, Skill.NONE, Skills.WardingBlow.lv(3), Skill.NONE, Skills.WardCavalry, Skill.NONE)
        createItem("カムイ（夏）", 2, WeaponType.BTOME, MoveType.FLIER, "Corrin(F) (Summer)", 5, 17, 7, 8, 5, 7, 4, 7, 8, 4, 5,
                Weapons.SealifeTome2, Skill.NONE, Specials.DragonFang, Skills.SwiftStrike.lv(2), Skill.NONE, Skills.FortifyFliers, Skill.NONE)
        createItem("シグレ（舞踏祭）", 2, WeaponType.BTOME, MoveType.INFANTRY, "Shigure (Performing Arts)", 5, 15, 7, 5, 4, 5, 5, 7, 7, 4, 5,
                Weapons.DancersScore2, Assists.Sing, Skill.NONE, Skill.NONE, Skills.GeyserDance.lv(2), Skills.BTomeValor.lv(3), Skill.NONE)
        createItem("ティルテュ", 2, WeaponType.BTOME, MoveType.INFANTRY, "Tailtiu", 5, 17, 8, 9, 4, 6, 6, 7, 8, 2, 5,
                Weapons.Blarblade2, Assists.RallySpdRes, Skill.NONE, Skills.AtkRes.lv(2), Skill.NONE, Skills.DriveSpd.lv(2), Skill.NONE)
        createItem("デューテ", 2, WeaponType.BTOME, MoveType.INFANTRY, "Delthea", 5, 16, 10, 8, 3, 7, 4, 8, 8, 1, 7,
                Weapons.DarkAura, Skill.NONE, Specials.Miracle, Skills.DeathBlow.lv(3), Skill.NONE, Skills.DriveAtk.lv(2), Skill.NONE)
        createItem("メイ", 2, WeaponType.BTOME, MoveType.INFANTRY, "Mae", 5, 16, 10, 7, 3, 8, 5, 8, 7, 2, 6,
                Weapons.Blarowl2, Assists.DrawBack, Skill.NONE, Skill.NONE, Skills.Desperation.lv(3), Skills.BTomeExperience.lv(3), Skill.NONE)
        createItem("ラインハルト", 2, WeaponType.BTOME, MoveType.CAVALRY, "Reinhardt", 4, 16, 8, 6, 5, 8, 6, 7, 4, 6, 4,
                Weapons.DireThunder, Skill.NONE, Specials.BlazingThunder, Skill.NONE, Skills.Vantage.lv(3), Skills.GoadCavalry, Skill.NONE)
        createItem("リンダ", 2, WeaponType.BTOME, MoveType.INFANTRY, "Linde", 5, 16, 9, 10, 4, 5, 5, 8, 8, 1, 6,
                Weapons.Aura, Assists.ArdentSacrifice, Skill.NONE, Skills.Speed.lv(3), Skill.NONE, Skills.FortifyRes.lv(3), Skill.NONE)
        createItem("ルフレ（男）", 2, WeaponType.BTOME, MoveType.INFANTRY, "Robin(M)", 3, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Weapons.Blarraven2, Skill.NONE, Specials.Boonfire, Skills.DefiantSpd.lv(3), Skill.NONE, Skills.SpurDef.lv(3), Skill.NONE)
        createItem("ニニアン", 2, WeaponType.BREATH, MoveType.DRAGON, "Ninian", 5, 16, 5, 7, 6, 5, 8, 5, 8, 4, 6,
                Weapons.LightBreath2, Assists.Dance, Skill.NONE, Skill.NONE, Skills.EscapeRoute.lv(3), Skills.FortifyDragons, Skill.NONE)
        createItem("ノノ", 2, WeaponType.BREATH, MoveType.DRAGON, "Nowi", 4, 17, 6, 5, 6, 5, 9, 9, 6, 7, 6,
                Weapons.LightningBreath2, Assists.RallyDefense, Skill.NONE, Skills.Defense.lv(3), Skill.NONE, Skills.ThreatenRes.lv(3), Skill.NONE)
        createItem("アイク（総選挙）", 3, WeaponType.AXE, MoveType.INFANTRY, "Ike (Brave Heroes)", 5, 17, 10, 6, 9, 5, 8, 8, 6, 8, 3,
                Weapons.Urvan, Skill.NONE, Specials.Aether, Skills.SteadyBreath, Skills.BeorcsBlessing, Skills.ThreatenDef.lv(3), Skill.NONE)
        createItem("アクア（舞踏祭）", 3, WeaponType.AXE, MoveType.INFANTRY, "Azura (Performing Arts)", 5, 16, 6, 8, 3, 6, 5, 8, 8, 4, 6,
                Weapons.Uror, Assists.Sing, Skill.NONE, Skills.TriangleAdept.lv(3), Skill.NONE, Skills.DriveRes.lv(2), Skill.NONE)
        createItem("アメリア", 3, WeaponType.AXE, MoveType.ARMORED, "Amelia", 5, 19, 6, 8, 9, 4, 9, 9, 8, 8, 5,
                Weapons.SlayingAxe2, Skill.NONE, Specials.HolyVestments, Skills.EarthBoost.lv(3), Skill.NONE, Skills.ArmorMarch.lv(3), Skill.NONE)
        createItem("アンナ", 3, WeaponType.AXE, MoveType.INFANTRY, "Anna", 2, 19, 7, 10, 5, 6, 6, 6, 9, 4, 6,
                Weapons.Noatun, Skill.NONE, Specials.Astra, Skill.NONE, Skills.Vantage.lv(3), Skills.SpurRes.lv(3), Skill.NONE)
        createItem("カミラ", 3, WeaponType.AXE, MoveType.FLIER, "Camilla", 4, 18, 8, 8, 6, 7, 5, 6, 7, 6, 7,
                Weapons.BraveAxe2, Skill.NONE, Specials.DragonGaze, Skills.DartingBlow.lv(3), Skill.NONE, Skills.SavageBlow.lv(3), Skill.NONE)
        createItem("ギュンター", 3, WeaponType.AXE, MoveType.CAVALRY, "Gunter", 3, 21, 10, 7, 11, 5, 6, 6, 4, 6, 2,
                Weapons.SilverAxe2, Assists.HarshCommand, Skill.NONE, Skills.ArmoredBlow.lv(3), Skill.NONE, Skills.HoneCavalry, Skill.NONE)
        createItem("クロム（春）", 3, WeaponType.AXE, MoveType.INFANTRY, "Chrom (Spring)", 5, 19, 9, 8, 6, 5, 7, 8, 7, 6, 3,
                Weapons.CarrotAxe2, Assists.Shove, Skill.NONE, Skills.AtkDef.lv(2), Skill.NONE, Skills.AxeExperience.lv(3), Skill.NONE)
        createItem("シーマ", 3, WeaponType.AXE, MoveType.ARMORED, "Sheena", 4, 21, 8, 6, 12, 7, 7, 6, 5, 7, 8,
                Weapons.KillerAxe2, Skill.NONE, Specials.Escutcheon, Skills.SvalinnShield, Skill.NONE, Skills.FortifyArmor, Skill.NONE)
        createItem("セルジュ", 3, WeaponType.AXE, MoveType.FLIER, "Cherche", 3, 20, 10, 6, 8, 3, 8, 9, 5, 7, 2,
                Weapons.Hammer2, Assists.Pivot, Skill.NONE, Skills.Attack.lv(3), Skill.NONE, Skills.FortifyDef.lv(3), Skill.NONE)
        createItem("チキ（夏）", 3, WeaponType.AXE, MoveType.INFANTRY, "Tiki(A) (Summer)", 5, 18, 8, 6, 8, 7, 4, 9, 7, 7, 4,
                Weapons.MelonCrusher2, Skill.NONE, Specials.Sol, Skills.CloseDef.lv(3), Skill.NONE, Skills.AxeValor.lv(3), Skill.NONE)
        createItem("ティアマト", 3, WeaponType.AXE, MoveType.CAVALRY, "Titania", 5, 18, 6, 8, 6, 8, 5, 6, 8, 5, 6,
                Weapons.EmeraldAxe2, Assists.ReciprocalAid, Skill.NONE, Skills.ArmoredBlow.lv(3), Skills.Guard.lv(3), Skill.NONE, Skill.NONE)
        createItem("ナーシェン", 3, WeaponType.AXE, MoveType.FLIER, "Narcian", 2, 18, 7, 7, 8, 7, 7, 6, 6, 7, 5,
                Weapons.EmeraldAxe2, Skill.NONE, Specials.Vengeance, Skill.NONE, Skills.LanceBreaker.lv(3), Skills.SavageBlow.lv(3), Skill.NONE)
        createItem("ニノ", 3, WeaponType.GTOME, MoveType.INFANTRY, "Nino", 3, 16, 7, 10, 4, 7, 4, 8, 8, 3, 5,
                Weapons.Gronnblade2, Assists.DrawBack, Skill.NONE, Skills.Resistance.lv(3), Skill.NONE, Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("バーツ", 3, WeaponType.AXE, MoveType.INFANTRY, "Barst", 3, 20, 9, 8, 6, 4, 8, 7, 7, 7, 2,
                Weapons.BraveAxe2, Assists.Reposition, Skill.NONE, Skill.NONE, Skills.KnockBack, Skills.SpurAtk.lv(3), Skill.NONE)
        createItem("バアトル", 3, WeaponType.AXE, MoveType.INFANTRY, "Bartre", 3, 21, 10, 6, 7, 3, 9, 8, 5, 8, 1,
                Weapons.Hammer2, Assists.Smite, Skill.NONE, Skills.Furry.lv(3), Skills.BrashAssault.lv(3), Skill.NONE, Skill.NONE)
        createItem("ハロルド", 3, WeaponType.AXE, MoveType.INFANTRY, "Arthur", 3, 19, 8, 7, 8, 5, 7, 7, 6, 6, 5,
                Weapons.EmeraldAxe2, Assists.Swap, Skill.NONE, Skills.Hp.lv(3), Skills.LanceBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("ファ", 3, WeaponType.BREATH, MoveType.DRAGON, "Fae", 4, 16, 5, 4, 6, 8, 10, 9, 7, 5, 6,
                Weapons.LightBreath2, Assists.DrawBack, Skill.NONE, Skill.NONE, Skills.Renewal.lv(3), Skills.ThreatenAtk.lv(3), Skill.NONE)
        createItem("フレデリク", 3, WeaponType.AXE, MoveType.CAVALRY, "Frederick", 3, 19, 9, 6, 8, 4, 7, 8, 5, 9, 1,
                Weapons.Hammer2, Skill.NONE, Specials.Luna, Skill.NONE, Skills.WingsOfMercy.lv(3), Skills.FortifyDef.lv(3), Skill.NONE)
        createItem("ヘクトル", 3, WeaponType.AXE, MoveType.ARMORED, "Hector", 5, 24, 10, 5, 11, 4, 9, 8, 5, 8, 3,
                Weapons.Armoads, Skill.NONE, Specials.Pavise, Skills.DistantCounter, Skill.NONE, Skills.GoadArmor, Skill.NONE)
        createItem("ベルカ", 3, WeaponType.AXE, MoveType.FLIER, "Beruka", 3, 20, 7, 6, 9, 5, 8, 6, 4, 9, 4,
                Weapons.KillerAxe2, Skill.NONE, Specials.Glimmer, Skills.DefiantDef.lv(3), Skills.Lunge, Skill.NONE, Skill.NONE)
        createItem("ホークアイ", 3, WeaponType.AXE, MoveType.INFANTRY, "Hawkeye", 4, 21, 9, 5, 6, 6, 7, 7, 4, 6, 7,
                Weapons.KillerAxe2, Skill.NONE, Specials.GrowingLight, Skills.DeathBlow.lv(3), Skill.NONE, Skills.ThreatenAtk.lv(3), Skill.NONE)
        createItem("マークス（夏）", 3, WeaponType.AXE, MoveType.INFANTRY, "Xander (Summer)", 5, 19, 8, 6, 8, 6, 7, 7, 7, 9, 1,
                Weapons.LilithFloatie2, Skill.NONE, Specials.Boonfire, Skills.FireBoost.lv(3), Skill.NONE, Skills.InfantryPulse.lv(3), Skill.NONE)
        createItem("ミシェイル", 3, WeaponType.AXE, MoveType.FLIER, "Michalis", 3, 19, 8, 7, 9, 4, 7, 8, 5, 8, 3,
                Weapons.Hauteclere, Skill.NONE, Specials.BlazingThunder, Skills.IotesShield, Skill.NONE, Skills.ThreatenDef.lv(3), Skill.NONE)
        createItem("ミネルバ", 3, WeaponType.AXE, MoveType.FLIER, "Minerva", 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Weapons.Hauteclere, Skill.NONE, Specials.SacredCowl, Skills.LifeAndDeath.lv(3), Skill.NONE, Skills.WardFliers, Skill.NONE)
        createItem("レイヴァン", 3, WeaponType.AXE, MoveType.INFANTRY, "Raven", 4, 19, 8, 9, 6, 5, 6, 8, 8, 5, 4,
                Weapons.BraveAxe2, Skill.NONE, Specials.Sol, Skills.DefiantSpd.lv(3), Skill.NONE, Skills.ThreatenDef.lv(3), Skill.NONE)
        createItem("ローロー", 3, WeaponType.AXE, MoveType.INFANTRY, "Legion", 3, 20, 10, 9, 5, 3, 8, 8, 8, 4, 3,
                Weapons.LegionsAxe2, Skill.NONE, Specials.Reprisal, Skills.Furry.lv(3), Skills.Obstruct.lv(3), Skill.NONE, Skill.NONE)
        createItem("アズール（舞踏祭）", 3, WeaponType.GTOME, MoveType.INFANTRY, "Inigo (Performing Arts)", 5, 15, 6, 7, 5, 3, 6, 6, 8, 4, 4,
                Weapons.DancersRing2, Assists.Dance, Skill.NONE, Skill.NONE, Skills.GaleDance.lv(3), Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("エリーゼ（夏）", 3, WeaponType.GTOME, MoveType.INFANTRY, "Elise (Summer)", 5, 17, 10, 8, 3, 6, 4, 8, 8, 3, 5,
                Weapons.HibiscusTome2, Assists.RallyAtkRes, Skill.NONE, Skills.SpdRes.lv(2), Skill.NONE, Skills.GTomeValor.lv(3), Skill.NONE)
        createItem("カミラ（春）", 3, WeaponType.GTOME, MoveType.FLIER, "Camilla (Spring)", 5, 17, 9, 6, 8, 4, 6, 8, 5, 6, 3,
                Weapons.GreenEgg2, Assists.RallyAttack, Skill.NONE, Skills.DefiantSpd.lv(3), Skills.LiveForBounty, Skill.NONE, Skill.NONE)
        createItem("セシリア", 3, WeaponType.GTOME, MoveType.CAVALRY, "Cecilia", 3, 17, 8, 6, 5, 7, 5, 7, 5, 4, 6,
                Weapons.Gronnraven2, Assists.RallyResistance, Skill.NONE, Skills.Attack.lv(3), Skills.EscapeRoute.lv(3), Skill.NONE, Skill.NONE)
        createItem("セネリオ", 3, WeaponType.GTOME, MoveType.INFANTRY, "Soren", 5, 17, 7, 9, 4, 7, 5, 8, 7, 2, 6,
                Weapons.Rexcalibur2, Skill.NONE, Specials.GrowingWind, Skill.NONE, Skills.Watersweep.lv(3), Skills.FortifyRes.lv(3), Skill.NONE)
        createItem("ソニア", 3, WeaponType.GTOME, MoveType.INFANTRY, "Sonya", 5, 17, 7, 7, 5, 8, 5, 8, 7, 1, 7,
                Weapons.DarkExcalibur, Skill.NONE, Specials.Moonbow, Skills.DeathBlow.lv(3), Skill.NONE, Skills.ResPloy.lv(3), Skill.NONE)
        createItem("ディアドラ", 3, WeaponType.GTOME, MoveType.INFANTRY, "Deirdre", 5, 17, 9, 6, 3, 9, 5, 7, 6, 2, 8,
                Weapons.DivineNaga, Assists.ArdentSacrifice, Skill.NONE, Skill.NONE, Skills.QuickRiposte.lv(3), Skills.SpdPloy.lv(3), Skill.NONE)
        createItem("ボーイ", 3, WeaponType.GTOME, MoveType.INFANTRY, "Boey", 5, 19, 7, 5, 8, 5, 7, 6, 6, 7, 2,
                Weapons.Gronnowl2, Skill.NONE, Specials.Ignis, Skills.EarthBoost.lv(3), Skills.Renewal.lv(3), Skill.NONE, Skill.NONE)
        createItem("マリク", 3, WeaponType.GTOME, MoveType.INFANTRY, "Merric", 4, 19, 7, 8, 6, 4, 7, 5, 7, 6, 3,
                Weapons.Excalibur, Skill.NONE, Specials.GrowingWind, Skills.Hp.lv(3), Skill.NONE, Skills.SpurRes.lv(3), Skill.NONE)
        createItem("ユリア", 3, WeaponType.GTOME, MoveType.INFANTRY, "Julia", 5, 16, 9, 7, 4, 8, 6, 8, 5, 2, 7,
                Weapons.Naga, Skill.NONE, Specials.DragonFang, Skills.Resistance.lv(3), Skill.NONE, Skills.BreathOfLife.lv(3), Skill.NONE)
        createItem("ルフレ（女）", 3, WeaponType.GTOME, MoveType.INFANTRY, "Robin(F)", 2, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Weapons.Gronnwolf2, Skill.NONE, Specials.Ignis, Skills.DefiantRes.lv(3), Skills.BTomeBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("アサマ", 0, WeaponType.STAFF, MoveType.INFANTRY, "Azama", 3, 19, 4, 7, 8, 6, 7, 4, 5, 7, 5,
                Weapons.Pain, Assists.Martyr, Specials.SolidEarthBalm, Skill.NONE, Skill.NONE, Skills.ThreatenAtk.lv(3), Skill.NONE)
        createItem("ヴィオール", 0, WeaponType.BOW, MoveType.INFANTRY, "Virion", 3, 20, 7, 7, 7, 3, 8, 7, 7, 5, 1,
                Weapons.SilverBow2, Skill.NONE, Specials.Astra, Skills.DefiantRes.lv(3), Skills.SealSpd.lv(3), Skill.NONE, Skill.NONE)
        createItem("エフィ", 0, WeaponType.BOW, MoveType.INFANTRY, "Faye", 5, 16, 6, 3, 4, 7, 8, 7, 6, 6, 7,
                Weapons.FiresweepBow2, Skill.NONE, Specials.Noontime, Skill.NONE, Skills.WingsOfMercy.lv(3), Skills.BowExperience.lv(3), Skill.NONE)
        createItem("エリーゼ", 0, WeaponType.STAFF, MoveType.CAVALRY, "Elise", 5, 15, 8, 8, 4, 8, 3, 7, 7, 3, 7,
                Weapons.Gravity, Assists.Recover, Specials.KindledFireBalm, Skill.NONE, Skills.LiveToServe.lv(3), Skill.NONE, Skill.NONE)
        createItem("オリヴィエ（舞踏祭）", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Olivia (Performing Arts)", 5, 15, 6, 8, 3, 4, 5, 6, 8, 2, 7,
                Weapons.DancersFan2, Assists.Dance, Skill.NONE, Skills.DistantDef.lv(3), Skills.BlazeDance.lv(3), Skill.NONE, Skill.NONE)
        createItem("ガイア", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Gaius", 3, 18, 7, 10, 5, 4, 7, 6, 8, 4, 3,
                Weapons.RogueDagger2, Assists.RallySpeed, Skill.NONE, Skills.DefiantAtk.lv(3), Skills.Pass.lv(3), Skill.NONE, Skill.NONE)
        createItem("ガイア（夏）", 0, WeaponType.BOW, MoveType.INFANTRY, "Gaius (Summer)", 5, 17, 8, 9, 4, 6, 4, 6, 9, 3, 6,
                Weapons.RefreshingBolt2, Skill.NONE, Specials.Astra, Skill.NONE, Skills.Vantage.lv(3), Skills.DefPloy.lv(3), Skill.NONE)
        createItem("カゲロウ", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Kagero", 4, 16, 9, 8, 5, 6, 3, 8, 7, 4, 6,
                Weapons.PoisonDagger2, Skill.NONE, Specials.Reprisal, Skills.WardingBlow.lv(3), Skills.DaggerBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("クライネ", 0, WeaponType.BOW, MoveType.INFANTRY, "Clarisse", 5, 18, 7, 8, 6, 5, 5, 7, 8, 5, 3,
                Weapons.ClarissesBow2, Skill.NONE, Specials.Glimmer, Skill.NONE, Skills.PoisonStrike.lv(3), Skills.ThreatenDef.lv(3), Skill.NONE)
        createItem("クラリーネ", 0, WeaponType.STAFF, MoveType.CAVALRY, "Clarine", 3, 16, 6, 9, 5, 7, 5, 5, 7, 4, 6,
                Weapons.Fear, Assists.Martyr, Specials.SwiftWindsBalm, Skills.Resistance.lv(3), Skill.NONE, Skill.NONE, Skill.NONE)
        createItem("クレイン", 0, WeaponType.BOW, MoveType.INFANTRY, "Klein", 4, 18, 9, 7, 5, 5, 6, 6, 8, 3, 5,
                Weapons.BraveBow2, Skill.NONE, Specials.Glacies, Skills.DeathBlow.lv(3), Skills.QuickRiposte.lv(3), Skill.NONE, Skill.NONE)
        createItem("ゴードン", 0, WeaponType.BOW, MoveType.INFANTRY, "Gordin", 3, 19, 7, 6, 8, 4, 7, 7, 5, 7, 2,
                Weapons.BraveBow2, Assists.Shove, Skill.NONE, Skills.Attack.lv(3), Skills.Vantage.lv(3), Skill.NONE, Skill.NONE)
        createItem("サイゾウ", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Saizo", 3, 17, 7, 8, 9, 3, 5, 6, 8, 7, 2,
                Weapons.SmokeDagger2, Assists.HarshCommand, Skill.NONE, Skill.NONE, Skills.PoisonStrike.lv(3), Skills.SpurSpd.lv(3), Skill.NONE)
        createItem("サクラ", 0, WeaponType.STAFF, MoveType.INFANTRY, "Sakura", 4, 17, 6, 8, 5, 8, 5, 6, 6, 5, 6,
                Weapons.Fear, Assists.Physic, Specials.StillWaterBalm, Skill.NONE, Skill.NONE, Skills.FortifyDef.lv(3), Skill.NONE)
        createItem("ジェニー", 0, WeaponType.STAFF, MoveType.INFANTRY, "Genny", 5, 17, 9, 6, 4, 8, 3, 8, 5, 4, 8,
                Weapons.Gravity, Assists.Physic, Specials.HeavenlyLight, Skill.NONE, Skills.WrathfulStaff.lv(3), Skill.NONE, Skill.NONE)
        createItem("ジャファル", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Jaffar", 5, 17, 7, 9, 6, 5, 7, 5, 7, 5, 4,
                Weapons.DeathlyDagger, Skill.NONE, Specials.Glimmer, Skills.LifeAndDeath.lv(3), Skill.NONE, Skills.ThreatenSpd.lv(3), Skill.NONE)
        createItem("ジョーカー", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Jakob", 4, 17, 7, 9, 6, 5, 6, 6, 6, 5, 5,
                Weapons.SilverDagger2, Assists.RallyResistance, Skill.NONE, Skills.Defense.lv(3), Skills.Renewal.lv(3), Skill.NONE, Skill.NONE)
        createItem("ジョルジュ", 0, WeaponType.BOW, MoveType.INFANTRY, "Jeorge", 4, 18, 8, 8, 5, 5, 5, 7, 7, 5, 4,
                Weapons.Parthia, Skill.NONE, Specials.BlazingFlame, Skill.NONE, Skills.SealAtk.lv(3), Skills.SpurSpd.lv(3), Skill.NONE)
        createItem("セーラ", 0, WeaponType.STAFF, MoveType.INFANTRY, "Serra", 3, 16, 6, 9, 4, 9, 4, 7, 6, 4, 7,
                Weapons.Absorb, Assists.Recover, Specials.SwiftWindsBalm, Skill.NONE, Skill.NONE, Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("セツナ", 0, WeaponType.BOW, MoveType.INFANTRY, "Setsuna", 3, 18, 6, 9, 5, 6, 5, 6, 9, 4, 4,
                Weapons.AssassinsBow2, Assists.ReciprocalAid, Skill.NONE, Skills.Hp.lv(3), Skills.BowBreaker.lv(3), Skill.NONE, Skill.NONE)
        createItem("ゼロ", 0, WeaponType.BOW, MoveType.INFANTRY, "Niles", 3, 18, 6, 8, 4, 8, 5, 5, 8, 2, 8,
                Weapons.KillerBow2, Skill.NONE, Specials.Iceberg, Skills.WardingBlow.lv(3), Skill.NONE, Skills.SpurRes.lv(3), Skill.NONE)
        createItem("タクミ", 0, WeaponType.BOW, MoveType.INFANTRY, "Takumi", 5, 18, 8, 7, 6, 5, 6, 7, 8, 5, 2,
                Weapons.FujinYumi, Skill.NONE, Specials.Vengeance, Skills.CloseCounter, Skill.NONE, Skills.ThreatenSpd.lv(3), Skill.NONE)
        createItem("ティアモ（花嫁）", 0, WeaponType.BOW, MoveType.INFANTRY, "Cordelia (Bride)", 5, 17, 9, 9, 4, 5, 5, 8, 8, 3, 4,
                Weapons.CupidArrow2, Assists.RallyAtkSpd, Skill.NONE, Skill.NONE, Skills.EscapeRoute.lv(3), Skills.BreathOfLife.lv(3), Skill.NONE)
        createItem("ヒーニアス", 0, WeaponType.BOW, MoveType.INFANTRY, "Innes", 5, 16, 9, 8, 4, 7, 5, 7, 8, 1, 7,
                Weapons.Nidhogg, Skill.NONE, Specials.Iceberg, Skills.FortressRes.lv(3), Skills.CancelAffinity.lv(3), Skill.NONE, Skill.NONE)
        createItem("フェリシア", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Felicia", 3, 15, 6, 11, 3, 9, 5, 4, 8, 3, 8,
                Weapons.SilverDagger2, Skill.NONE, Specials.Glacies, Skills.Resistance.lv(3), Skill.NONE, Skills.BreathOfLife.lv(3), Skill.NONE)
        createItem("プリシラ", 0, WeaponType.STAFF, MoveType.CAVALRY, "Priscilla", 4, 17, 7, 7, 4, 8, 5, 6, 6, 3, 7,
                Weapons.Panic, Assists.Rehabilitate, Specials.StillWaterBalm, Skill.NONE, Skill.NONE, Skills.SpurDef.lv(3), Skill.NONE)
        createItem("フレデリク（夏）", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Frederick (Summer)", 5, 18, 8, 7, 6, 5, 6, 7, 7, 6, 2,
                Weapons.Seashell2,Assists.ArdentSacrifice, Skill.NONE, Skills.ArmoredBlow.lv(3), Skills.SealAtkSpd.lv(2), Skill.NONE, Skill.NONE)
        createItem("マシュー", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Matthew", 3, 17, 6, 10, 6, 5, 7, 5, 7, 7, 2,
                Weapons.RogueDagger2, Assists.ReciprocalAid, Skill.NONE, Skill.NONE, Skills.PoisonStrike.lv(3), Skills.HoneSpd.lv(3), Skill.NONE)
        createItem("マリア", 0, WeaponType.STAFF, MoveType.INFANTRY, "Maria", 4, 17, 5, 8, 4, 10, 5, 6, 8, 3, 6,
                Weapons.Panic, Assists.Physic, Specials.Miracle, Skill.NONE, Skill.NONE, Skills.FortifyRes.lv(3), Skill.NONE)
        createItem("ミスト", 0, WeaponType.STAFF, MoveType.INFANTRY, "Mist", 5, 17, 8, 6, 5, 8, 6, 5, 6, 3, 8,
                Weapons.Slow, Assists.Recover, Specials.Miracle, Skill.NONE, Skill.NONE, Skills.SpurDefRes.lv(2), Skill.NONE)
        createItem("ラケシス", 0, WeaponType.STAFF, MoveType.INFANTRY, "Lachesis", 4, 17, 6, 8, 5, 8, 6, 8, 4, 4, 6,
                Weapons.Absorb, Assists.Physic, Specials.SolidEarthBalm, Skill.NONE, Skill.NONE, Skills.SpurRes.lv(3), Skill.NONE)
        createItem("リズ", 0, WeaponType.STAFF, MoveType.INFANTRY, "Lissa", 3, 17, 7, 6, 6, 8, 6, 5, 5, 6, 6,
                Weapons.Gravity, Assists.Rehabilitate, Specials.KindledFireBalm, Skill.NONE, Skills.Renewal.lv(3), Skill.NONE, Skill.NONE)
        createItem("リフ", 0, WeaponType.STAFF, MoveType.INFANTRY, "Wrys", 3, 18, 5, 6, 5, 10, 7, 5, 4, 4, 8,
                Weapons.Slow, Assists.Rehabilitate, Specials.HeavenlyLight, Skill.NONE, Skills.LiveToServe.lv(3), Skill.NONE, Skill.NONE)
        createItem("リン（花嫁）", 0, WeaponType.STAFF, MoveType.INFANTRY, "Lyn (Bride)", 5, 17, 6, 10, 6, 5, 6, 6, 7, 4, 5,
                Weapons.Candlelight, Assists.Rehabilitate, Specials.SwiftWindsBalm, Skill.NONE, Skills.DazzlingStaff.lv(3), Skill.NONE, Skill.NONE)
        createItem("リン（総選挙）", 0, WeaponType.BOW, MoveType.CAVALRY, "Lyn (Brave Heroes)", 5, 16, 7, 9, 5, 6, 5, 8, 8, 2, 6,
                Weapons.Mulagir, Skill.NONE, Specials.DragonGaze, Skills.SwiftSparrow.lv(2), Skills.SacaesBlessing, Skills.AtkSmoke.lv(3), Skill.NONE)
        createItem("ルセア", 0, WeaponType.STAFF, MoveType.INFANTRY, "Lucius", 4, 18, 6, 8, 3, 9, 5, 8, 6, 1, 8,
                Weapons.Pain, Assists.Martyr, Specials.Miracle, Skills.Hp.lv(3), Skill.NONE, Skill.NONE, Skill.NONE)
        createItem("レオ", 0, WeaponType.BOW, MoveType.INFANTRY, "Leon", 5, 17, 8, 6, 8, 5, 6, 8, 7, 6, 1,
                Weapons.SlayingBow2, Skill.NONE, Specials.Ignis, Skills.Speed.lv(3), Skills.Guard.lv(3), Skill.NONE, Skill.NONE)
        createItem("レベッカ", 0, WeaponType.BOW, MoveType.INFANTRY, "Rebecca", 4, 18, 7, 8, 6, 5, 5, 6, 8, 3, 6,
                Weapons.SilverBow2, Assists.ArdentSacrifice, Skill.NONE, Skills.DartingBlow.lv(3), Skills.SealAtk.lv(3), Skill.NONE, Skill.NONE)

        createItem("ヘンリー（収穫祭）", 0, WeaponType.GTOME, MoveType.INFANTRY, "Henry (Trick or Defeat)", 5, 17, 9, 10, 4, 12, 5, 7, 7, 6, 7,
                Weapons.SpectralTome2, Skill.NONE, Specials.Reprisal, Skill.NONE, Skills.LiveForHonor, Skills.ArmorMarch.lv(3), Skill.NONE)
        createItem("ジョーカー（収穫祭）", 0, WeaponType.BOW, MoveType.ARMORED, "Jakob (Trick or Defeat)", 5, 20, 9, 6, 9, 8, 6, 8, 4, 7, 7,
                Weapons.MonstrousBow2, Skill.NONE, Skill.NONE, Skills.BracingBlow.lv(2), Skills.WaryFighter.lv(3), Skill.NONE, Skill.NONE)
        createItem("ノノ（収穫祭）", 1, WeaponType.RTOME, MoveType.FLIER, "Nowi (Trick or Defeat)", 5, 17, 8, 6, 5, 8, 4, 8, 8, 3, 5,
                Weapons.Grimoire, Assists.Reposition, Skill.NONE, Skills.AtkResBond.lv(3), Skills.LiveForBounty, Skills.HoneAtk.lv(3), Skill.NONE)
        createItem("サクラ（収穫祭）", 0, WeaponType.DAGGER, MoveType.INFANTRY, "Sakura (Trick or Defeat)", 5, 16, 8, 8, 4, 8, 4, 7, 8, 1, 8,
                Weapons.KittyPaddle2, Skill.NONE, Specials.Glacies, Skills.WardingStance.lv(3), Skills.Guard.lv(3), Skills.DaggerValor.lv(3), Skill.NONE)

        createItem("ドルカス", 3, WeaponType.AXE, MoveType.INFANTRY, "Dorcas", 5, 19, 9, 6, 9, 5, 8, 8, 4, 8, 5,
                Weapons.StoutTomahawk, Skill.NONE, Specials.DragonGaze, Skills.FierceStance.lv(3), Skills.QuickRiposte.lv(3), Skills.InfantryPulse.lv(3), Skill.NONE)
        createItem("ルーテ", 2, WeaponType.BTOME, MoveType.INFANTRY, "Lute", 5, 16, 10, 8, 3, 8, 4, 8, 7, 2, 8,
                Weapons.WeirdingTome, Assists.RallyAtkRes, Skill.NONE, Skills.HpRes.lv(2), Skill.NONE, Skills.ResPloy.lv(3), Skill.NONE)
        createItem("ワユ", 1, WeaponType.SWORD, MoveType.INFANTRY, "Mia", 5, 16, 8, 12, 6, 6, 6, 7, 9, 6, 5,
                Weapons.ResoluteBlade, Skill.NONE, Specials.Luna, Skills.FlashingBlade.lv(3), Skills.Vantage.lv(3), Skill.NONE, Skill.NONE)

    }

    fun createItem(
            name: String = "",
            color: Int = 0,
            weaponType: WeaponType = WeaponType.SWORD,
            moveType: MoveType = MoveType.INFANTRY,
            usName: String = "",
            minRarity: Int = 0,
            hp: Int = 0,
            atk: Int = 0,
            spd: Int = 0,
            def: Int = 0,
            res: Int = 0,
            hpgrowth: Int = 0,
            atkgrowth: Int = 0,
            spdgrowth: Int = 0,
            defgrowth: Int = 0,
            resgrowth: Int = 0,
            weapon: Skill = Skill.NONE,
            assist: Skill = Skill.NONE,
            special: Skill = Skill.NONE,
            aSkill: Skill = Skill.NONE,
            bSkill: Skill = Skill.NONE,
            cSkill: Skill = Skill.NONE,
            seal: Skill = Skill.NONE
    ) {
        val item = BattleClass(color, weaponType, moveType, minRarity, name, usName, hp, atk, spd, def, res, hpgrowth, atkgrowth, spdgrowth, defgrowth, resgrowth, weapon, assist, special, aSkill, bSkill, cSkill, seal)
        ITEMS.add(item)
        ITEMMAP.put(item.name, item)
        ITEMMAP.put(item.usName, item)
    }

}