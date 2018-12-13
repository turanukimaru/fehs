package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.fehs.skill.*

/**
 *　キャラとその基準値
 */
object StandardBaseHero {
    //initブロックと同じ優先順位、つまりinitの前に書かないと作成されない
    private val ITEMS = ArrayList<BaseHero>()
    private val ITEM_MAP = HashMap<String, BaseHero>()
    fun get(id: String): BaseHero? = ITEM_MAP[id]?.copy()
    fun get(id: Int): BaseHero? = ITEMS[id].copy()
    fun containsKey(id: String): Boolean = ITEM_MAP.containsKey(id)
    fun allItems(): MutableList<BaseHero> = ITEMS.fold(mutableListOf()) { list, e -> list.add(e);list }

    init {
        creates()
    }

    private fun creates() {
        createItem(Name.アイク, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 9, 7, 8, 5, 7, 8, 7, 7, 2,
                Sword.Ragnell, null, Special.Aether, SkillA.HeavyBlade.lv(3), SkillB.SwordBreaker.lv(3), null)
        createItem(Name.アイラ, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 19, 7, 11, 7, 4, 6, 8, 8, 7, 4,
                Sword.AyrasBlade, null, Special.RegnalAstra, SkillA.SwiftSparrow.lv(2), SkillB.Desperation.lv(3), null)
        createItem(Name.アーダン, 1, WeaponType.SWORD, MoveType.ARMORED, 4, 25, 10, 3, 13, 3, 12, 8, 2, 9, 2,
                Sword.BraveSword2, null, Special.Pavise, SkillB.FollowUpRing, SkillC.DriveDef.lv(2))
        createItem(Name.アテナ, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 17, 7, 10, 8, 5, 5, 7, 9, 5, 5,
                Sword.WaoDao2, null, Special.Moonbow, SkillA.SturdyBlow.lv(2), null, SkillC.SwordExperience.lv(3))
        createItem(Name.アルフォンス, 1, WeaponType.SWORD, MoveType.INFANTRY, 2, 19, 9, 6, 8, 5, 7, 8, 5, 7, 4,
                Sword.Folkvangr, null, Special.Sol, SkillA.DeathBlow.lv(3), null, SkillC.SpurAtk.lv(3))
        createItem(Name.アルフォンス3, 1, WeaponType.SWORD, MoveType.INFANTRY, 2, 19, 9, 6, 8, 5, 7, 8, 5, 7, 4,
                Sword.Folkvangr, null, Special.Sol, SkillA.DeathBlow.lv(3), null, SkillC.SpurAtk.lv(3), RefinedWeapon.Folkvangr)
        createItem(Name.アルム, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 21, 9, 6, 6, 5, 7, 7, 7, 6, 4,
                Sword.FalchionA, null, Special.DraconicAura, SkillA.Attack.lv(3), SkillB.Windsweep.lv(3), null)
        createItem(Name.アルム3, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 21, 9, 6, 6, 5, 7, 7, 7, 6, 4,
                Sword.FalchionA, null, Special.DraconicAura, SkillA.Attack.lv(3), SkillB.Windsweep.lv(3), null, RefinedWeapon.FalchionA)
        createItem(Name.エイリーク, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 18, 7, 9, 7, 6, 7, 5, 8, 5, 6,
                Sword.Sieglinde, Assist.Pivot, null, null, SkillB.DragBack, SkillC.HoneSpd.lv(3))
        createItem(Name.エイリーク3, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 18, 7, 9, 7, 6, 7, 5, 8, 5, 6,
                Sword.Sieglinde, Assist.Pivot, null, null, SkillB.DragBack, SkillC.HoneSpd.lv(3), RefinedWeapon.Sieglinde)
        createItem(Name.エリウッド, 1, WeaponType.SWORD, MoveType.CAVALRY, 3, 17, 7, 8, 6, 8, 6, 7, 6, 4, 7,
                Sword.Durandal, null, Special.SacredCowl, null, SkillB.AxeBreaker.lv(3), SkillC.WardCavalry)
        createItem(Name.エリンシア, 1, WeaponType.SWORD, MoveType.FLIER, 5, 16, 8, 10, 5, 8, 5, 8, 8, 5, 5,
                Sword.Amiti, Assist.ArdentSacrifice, null, SkillA.DeathBlow.lv(3), SkillB.FlierFormation.lv(3))
        createItem(Name.エルトシャン, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 19, 8, 5, 8, 6, 8, 7, 5, 8, 2,
                Sword.Mystletainn, null, Special.GrowingLight, SkillA.Fury.lv(3), SkillB.Lunge, null)
        createItem(Name.エルトシャン3, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 19, 8, 5, 8, 6, 8, 7, 5, 8, 2,
                Sword.Mystletainn, null, Special.GrowingLight, SkillA.Fury.lv(3), SkillB.Lunge, null, RefinedWeapon.Mystletainn)
        createItem(Name.オグマ, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 21, 7, 10, 6, 3, 8, 9, 7, 6, 1,
                Sword.BraveSword2, null, Special.Noontime, SkillA.DefiantAtk.lv(3), null, SkillC.SpurAtk.lv(3))
        createItem(Name.オグマ2, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 21, 7, 10, 6, 3, 8, 9, 7, 6, 1,
                Sword.GladiatorsBlade, null, Special.Noontime, SkillA.DefiantAtk.lv(3), null, SkillC.SpurAtk.lv(3))
        createItem(Name.オグマ3, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 21, 7, 10, 6, 3, 8, 9, 7, 6, 1,
                Sword.GladiatorsBlade, null, Special.Noontime, SkillA.DefiantAtk.lv(3), null, SkillC.SpurAtk.lv(3),RefinedWeapon.GladiatorsBlade)
        createItem(Name.オリヴィエ, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 17, 6, 7, 5, 4, 5, 6, 8, 6, 6,
                Sword.SilverSword2, Assist.Dance, null, null, SkillB.KnockBack, SkillC.HoneAtk.lv(3))
        createItem(Name.カイン, 1, WeaponType.SWORD, MoveType.CAVALRY, 4, 18, 8, 6, 8, 6, 7, 7, 8, 5, 3,
                Sword.BraveSword2, null, Special.Escutcheon, null, SkillB.WingsOfMercy.lv(3), SkillC.ThreatenAtk.lv(3))
        createItem(Name.カザハナ, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 18, 9, 10, 6, 4, 5, 8, 8, 4, 6,
                Sword.ArmorSlayer2, Assist.RallyAttack, null, SkillA.LifeAndDeath.lv(3), SkillB.Obstruct.lv(3), null)
        createItem(Name.カムイ__男_, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 20, 8, 8, 6, 5, 6, 7, 7, 6, 5,
                Sword.Yato, null, Special.DragonFang, SkillA.Defense.lv(3), SkillB.Obstruct.lv(3), null)
        createItem(Name.カムイ__男_3, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 20, 8, 8, 6, 5, 6, 7, 7, 6, 5,
                Sword.Yato, null, Special.DragonFang, SkillA.Defense.lv(3), SkillB.Obstruct.lv(3), null, RefinedWeapon.Yato2)
        createItem(Name.カレル, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 19, 8, 9, 6, 5, 9, 6, 8, 5, 3,
                Sword.WaoDao2, null, Special.Reprisal, SkillA.DefiantAtk.lv(3), SkillB.Desperation.lv(3), null)
        createItem(Name.カレル2, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 19, 8, 9, 6, 5, 9, 6, 8, 5, 3,
                Sword.NamelessBlade, null, Special.Reprisal, SkillA.DefiantAtk.lv(3), SkillB.Desperation.lv(3), null)
        createItem(Name.グレイ, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 17, 7, 6, 6, 3, 8, 9, 8, 7, 5,
                Sword.Zanbato2, Assist.Swap, null, SkillA.WindBoost.lv(3), null, SkillC.SwordValor.lv(3))
        createItem(Name.クロム, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 21, 9, 6, 7, 4, 8, 9, 5, 7, 2,
                Sword.FalchionC, null, Special.Aether, SkillA.DefiantDef.lv(3), null, SkillC.SpurDef.lv(3))
        createItem(Name.クロム3, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 21, 9, 6, 7, 4, 8, 9, 5, 7, 2,
                Sword.FalchionC, null, Special.Aether, SkillA.DefiantDef.lv(3), null, SkillC.SpurDef.lv(3), RefinedWeapon.FalchionM)
        createItem(Name.シーダ, 1, WeaponType.SWORD, MoveType.FLIER, 4, 17, 6, 9, 5, 10, 5, 5, 9, 5, 7,
                Sword.ArmorSlayer2, Assist.RallySpeed, null, SkillA.DartingBlow.lv(3), null, SkillC.FortifyFliers)
        createItem(Name.シーダ2, 1, WeaponType.SWORD, MoveType.FLIER, 4, 17, 6, 9, 5, 10, 5, 5, 9, 5, 7,
                Sword.WingSword, Assist.RallySpeed, null, SkillA.DartingBlow.lv(3), null, SkillC.FortifyFliers)
        createItem(Name.シーダ3, 1, WeaponType.SWORD, MoveType.FLIER, 4, 17, 6, 9, 5, 10, 5, 5, 9, 5, 7,
                Sword.WingSword, Assist.RallySpeed, null, SkillA.DartingBlow.lv(3), null, SkillC.FortifyFliers, RefinedWeapon.WingSword)
        createItem(Name.シグルド, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 19, 9, 8, 6, 4, 6, 8, 7, 9, 2,
                Sword.DivineTyrfing, null, Special.Miracle, SkillA.CloseDef.lv(3), SkillB.CrusadersWard, SkillC.SpdSmoke.lv(3))
        createItem(Name.漆黒の騎士, 1, WeaponType.SWORD, MoveType.ARMORED, 4, 22, 10, 8, 9, 5, 8, 7, 8, 8, 2,
                Sword.Alondite, null, Special.BlackLuna, SkillA.SteadyStance.lv(3), SkillB.WingsOfMercy.lv(3), null)
        createItem(Name.セーバー, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Sword.SlayingEdge2, null, Special.Aegis, SkillA.HpSpd.lv(2), SkillB.ShieldPulse.lv(3), null)
        createItem(Name.セーバー2, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Sword.GoldenDagger, null, Special.Aegis, SkillA.HpSpd.lv(2), SkillB.ShieldPulse.lv(3), null)
        createItem(Name.セーバー3, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Sword.GoldenDagger, null, Special.Aegis, SkillA.HpSpd.lv(2), SkillB.ShieldPulse.lv(3), null, RefinedWeapon.GoldenDagger)
        createItem(Name.ゼト, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 18, 8, 7, 8, 5, 5, 7, 7, 7, 4,
                Sword.RubySword2, Assist.Swap, null, SkillA.FortressDef.lv(3), SkillB.SealAtkDef.lv(2), null)
        createItem(Name.ゼフィール, 1, WeaponType.SWORD, MoveType.ARMORED, 3, 25, 9, 3, 12, 5, 10, 8, 2, 8, 5,
                Sword.Eckesachs, null, Special.Reprisal, SkillA.LifeAndDeath.lv(3), SkillB.WaryFighter.lv(3), null)
        createItem(Name.ゼフィール3, 1, WeaponType.SWORD, MoveType.ARMORED, 3, 25, 9, 3, 12, 5, 10, 8, 2, 8, 5,
                Sword.Eckesachs, null, Special.Reprisal, SkillA.LifeAndDeath.lv(3), SkillB.WaryFighter.lv(3), null, RefinedWeapon.Eckesachs)
        createItem(Name.セリス, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 19, 8, 7, 8, 5, 9, 8, 4, 6, 4,
                Sword.Tyrfing, Assist.RallySpeed, null, SkillA.Hp.lv(3), SkillB.BrashAssault.lv(3), null)
        createItem(Name.セリス2, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 19, 8, 7, 8, 5, 9, 8, 4, 6, 4,
                Sword.DivineTyrfing, Assist.RallySpeed, null, SkillA.Hp.lv(3), SkillB.BrashAssault.lv(3), null)
        createItem(Name.ソール, 1, WeaponType.SWORD, MoveType.CAVALRY, 3, 19, 7, 7, 8, 5, 8, 7, 5, 6, 4,
                Sword.RubySword2, Assist.Swap, null, SkillA.Defense.lv(3), SkillB.Obstruct.lv(3), null)
        createItem(Name.ドーガ, 1, WeaponType.SWORD, MoveType.ARMORED, 2, 24, 8, 6, 13, 3, 8, 6, 8, 8, 3,
                Sword.BraveSword2, null, Special.Pavise, null, SkillB.Lunge, SkillC.WardArmor)
        createItem(Name.ナバール, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 18, 7, 11, 6, 5, 7, 7, 8, 4, 5,
                Sword.KillingEdge2, null, Special.BlazingWind, null, SkillB.Desperation.lv(3), SkillC.ThreatenSpd.lv(3))
        createItem(Name.ナバール2, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 18, 7, 11, 6, 5, 7, 7, 8, 4, 5,
                Sword.ScarletSword, null, Special.BlazingWind, null, SkillB.Desperation.lv(3), SkillC.ThreatenSpd.lv(3))
        createItem(Name.ナバール3, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 18, 7, 11, 6, 5, 7, 7, 8, 4, 5,
                Sword.ScarletSword, null, Special.BlazingWind, null, SkillB.Desperation.lv(3), SkillC.ThreatenSpd.lv(3),RefinedWeapon.ScarletSword)
        createItem(Name.パオラ, 1, WeaponType.SWORD, MoveType.FLIER, 3, 18, 7, 9, 6, 7, 7, 7, 6, 6, 5,
                Sword.RubySword2, null, Special.Moonbow, null, SkillB.WingsOfMercy.lv(3), SkillC.GoadFliers)
        createItem(Name.パオラ2, 1, WeaponType.SWORD, MoveType.FLIER, 3, 18, 7, 9, 6, 7, 7, 7, 6, 6, 5,
                Sword.WhitewingBlade, null, Special.Moonbow, null, SkillB.WingsOfMercy.lv(3), SkillC.GoadFliers)
        createItem(Name.パオラ3, 1, WeaponType.SWORD, MoveType.FLIER, 3, 18, 7, 9, 6, 7, 7, 7, 6, 6, 5,
                Sword.WhitewingBlade, null, Special.Moonbow, null, SkillB.WingsOfMercy.lv(3), SkillC.GoadFliers, RefinedWeapon.WhitewingBlade)
        createItem(Name.ヒナタ, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 21, 8, 5, 10, 3, 8, 7, 5, 8, 3,
                Sword.RubySword2, null, Special.Pavise, SkillA.Fury.lv(3), SkillB.BrashAssault.lv(3), null)
        createItem(Name.フィル, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 19, 6, 10, 5, 7, 6, 5, 8, 5, 7,
                Sword.KillingEdge2, null, Special.Glacies, SkillA.Speed.lv(3), SkillB.Pass.lv(3), null)
        createItem(Name.フィル2, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 19, 6, 10, 5, 7, 6, 5, 8, 5, 7,
                Sword.NamelessBlade, null, Special.Glacies, SkillA.Speed.lv(3), SkillB.Pass.lv(3), null)
        createItem(Name.フィル3, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 19, 6, 10, 5, 7, 6, 5, 8, 5, 7,
                Sword.NamelessBlade, null, Special.Glacies, SkillA.Speed.lv(3), SkillB.Pass.lv(3), null, RefinedWeapon.NamelessBlade)
        createItem(Name.マルス, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 19, 7, 8, 7, 6, 6, 7, 8, 6, 4,
                Sword.FalchionM, Assist.Pivot, null, null, SkillB.EscapeRoute.lv(3), SkillC.SpurSpd.lv(3))
        createItem(Name.マルス__仮面_, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 19, 8, 10, 6, 4, 7, 8, 8, 5, 3,
                Sword.FalchionC, null, null, null, null, null)
        createItem(Name.マークス, 1, WeaponType.SWORD, MoveType.CAVALRY, 3, 20, 8, 5, 9, 4, 7, 7, 5, 9, 2,
                Sword.Siegfried, null, Special.BlazingLight, SkillA.ArmoredBlow.lv(3), null, SkillC.SpurDef.lv(3))
        createItem(Name.ラズワルド, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 20, 9, 7, 6, 5, 7, 8, 5, 7, 4,
                Sword.SilverSword2, null, Special.Noontime, null, SkillB.AxeBreaker.lv(3), SkillC.HoneSpd.lv(3))
        createItem(Name.リョウマ, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 19, 8, 11, 5, 4, 6, 8, 7, 6, 4,
                Sword.Raijinto, null, Special.Astra, SkillA.DefiantAtk.lv(3), null, SkillC.HoneSpd.lv(3))
        createItem(Name.リン, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 6, 11, 7, 5, 5, 6, 8, 5, 7,
                Sword.SolKatti, null, Special.Galeforce, SkillA.DefiantAtk.lv(3), null, SkillC.SpurSpd.lv(3))
        createItem(Name.リン3, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 6, 11, 7, 5, 5, 6, 8, 5, 7,
                Sword.SolKatti, null, Special.Galeforce, SkillA.DefiantAtk.lv(3), null, SkillC.SpurSpd.lv(3), RefinedWeapon.SolKatti)
        createItem(Name.ルーク, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 19, 8, 6, 8, 5, 7, 8, 6, 6, 3,
                Sword.BraveSword2, null, Special.Bonfire, SkillA.FireBoost.lv(3), null, SkillC.PanicPloy.lv(3))
        createItem(Name.ルーナ, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 18, 6, 9, 8, 6, 5, 5, 8, 7, 6,
                Sword.ArmorSlayer2, Assist.Reposition, null, SkillA.TriangleAdept.lv(3), null, SkillC.ThreatenSpd.lv(3))
        createItem(Name.ルキナ, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 19, 8, 10, 6, 4, 7, 8, 8, 5, 3,
                Sword.FalchionC, null, Special.Aether, SkillA.DefiantSpd.lv(3), null, SkillC.SpurAtk.lv(3))
        createItem(Name.ルキナ3, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 19, 8, 10, 6, 4, 7, 8, 8, 5, 3,
                Sword.FalchionC, null, Special.Aether, SkillA.DefiantSpd.lv(3), null, SkillC.SpurAtk.lv(3), RefinedWeapon.FalchionC)
        createItem(Name.ロイ, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 20, 8, 9, 6, 4, 7, 6, 6, 5, 7,
                Sword.BindingBlade, Assist.Shove, null, SkillA.TriangleAdept.lv(3), SkillB.SealDef.lv(3), null)
        createItem(Name.ロイ3, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 20, 8, 9, 6, 4, 7, 6, 6, 5, 7,
                Sword.BindingBlade, Assist.Shove, null, SkillA.TriangleAdept.lv(3), SkillB.SealDef.lv(3), null, RefinedWeapon.BindingBlade2)
        createItem(Name.ロイ__総選挙_, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 16, 8, 8, 7, 7, 6, 7, 8, 5, 4,
                Sword.BlazingDurandal, null, Special.Galeforce, SkillA.SteadyBlow.lv(2), SkillB.Desperation.lv(3), null)
        createItem(Name.ロイド, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 17, 8, 9, 5, 8, 7, 7, 8, 3, 6,
                Sword.RegalBlade, null, Special.Iceberg, null, SkillB.Pass.lv(3), SkillC.ThreatenAtk.lv(3))
        createItem(Name.ロイド3, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 17, 8, 9, 5, 8, 7, 7, 8, 3, 6,
                Sword.RegalBlade, null, Special.Iceberg, null, SkillB.Pass.lv(3), SkillC.ThreatenAtk.lv(3), RefinedWeapon.RegalBlade)
        createItem(Name.ロビン, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 18, 7, 5, 5, 4, 9, 7, 6, 9, 6,
                Sword.ArmorSlayer2, Assist.Pivot, null, SkillA.Attack.lv(3), SkillB.SealSpd.lv(3), null)
        createItem(Name.ロンクー, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 19, 7, 11, 5, 5, 8, 6, 9, 4, 4,
                Sword.KillingEdge2, null, Special.Glimmer, SkillA.Speed.lv(3), SkillB.Vantage.lv(3), null)
        createItem(Name.ロンクー2, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 19, 7, 11, 5, 5, 8, 6, 9, 4, 4,
                Sword.SolitaryBlade, null, Special.Glimmer, SkillA.Speed.lv(3), SkillB.Vantage.lv(3), null)
        createItem(Name.ロンクー3, 1, WeaponType.SWORD, MoveType.INFANTRY, 3, 19, 7, 11, 5, 5, 8, 6, 9, 4, 4,
                Sword.SolitaryBlade, null, Special.Glimmer, SkillA.Speed.lv(3), SkillB.Vantage.lv(3), null, RefinedWeapon.SolitaryBlade)
        createItem(Name.アルヴィス, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 18, 8, 7, 4, 7, 3, 8, 7, 2, 8,
                Rtome.Valflame, null, Special.GrowingFlame, null, SkillB.RecoverRing, SkillC.DefPloy.lv(3))
        createItem(Name.カタリナ, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 17, 6, 8, 5, 8, 4, 8, 8, 1, 7,
                Rtome.Rauorowl2, null, Special.Glacies, SkillA.SwiftSparrow.lv(2), null, SkillC.AtkPloy.lv(3))
        createItem(Name.カタリナ2, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 17, 6, 8, 5, 8, 4, 8, 8, 1, 7,
                Rtome.ReesesTome, null, Special.Glacies, SkillA.SwiftSparrow.lv(2), null, SkillC.AtkPloy.lv(3))
        createItem(Name.カタリナ3, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 17, 6, 8, 5, 8, 4, 8, 8, 1, 7,
                Rtome.ReesesTome, null, Special.Glacies, SkillA.SwiftSparrow.lv(2), null, SkillC.AtkPloy.lv(3), RefinedWeapon.ReesesTome)
        createItem(Name.サーリャ, 1, WeaponType.RTOME, MoveType.INFANTRY, 4, 17, 8, 8, 6, 5, 6, 7, 8, 4, 3,
                Rtome.Rauorblade2, null, Special.Vengeance, SkillA.DartingBlow.lv(3), null, SkillC.SpurRes.lv(3))
        createItem(Name.サナキ, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 16, 9, 7, 4, 8, 4, 9, 5, 2, 8,
                Rtome.Cymbeline, Assist.HarshCommand, null, SkillA.TriangleAdept.lv(3), null, SkillC.HoneAtk.lv(3))
        createItem(Name.サナキ3, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 16, 9, 7, 4, 8, 4, 9, 5, 2, 8,
                Rtome.Cymbeline, Assist.HarshCommand, null, SkillA.TriangleAdept.lv(3), null, SkillC.HoneAtk.lv(3), RefinedWeapon.Cymbeline)
        createItem(Name.セリカ, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 17, 8, 7, 5, 7, 6, 7, 8, 4, 3,
                Rtome.Ragnarok, null, Special.BlazingLight, SkillA.DistantDef.lv(3), null, SkillC.SpurDef.lv(3))
        createItem(Name.セリカ3, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 17, 8, 7, 5, 7, 6, 7, 8, 4, 3,
                Rtome.Ragnarok, null, Special.BlazingLight, SkillA.DistantDef.lv(3), null, SkillC.SpurDef.lv(3), RefinedWeapon.Ragnarok)
        createItem(Name.ソフィーヤ, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 18, 9, 4, 6, 7, 6, 7, 3, 6, 6,
                Rtome.Fenrir2, null, Special.DragonFang, SkillA.WardingBlow.lv(3), null, SkillC.FortifyRes.lv(3))
        createItem(Name.ヘンリー, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 19, 6, 5, 8, 6, 8, 4, 4, 7, 5,
                Rtome.Rauorraven2, null, Special.Ignis, SkillA.DefiantDef.lv(3), SkillB.GTomeBreaker.lv(3), null)
        createItem(Name.リリーナ, 1, WeaponType.RTOME, MoveType.INFANTRY, 4, 16, 9, 6, 4, 9, 5, 9, 5, 3, 6,
                Rtome.Bolganone2, null, Special.GrowingFlame, SkillA.Attack.lv(3), null, SkillC.SpurAtk.lv(3))
        createItem(Name.リリーナ2, 1, WeaponType.RTOME, MoveType.INFANTRY, 4, 16, 9, 6, 4, 9, 5, 9, 5, 3, 6,
                Rtome.Forblaze, null, Special.GrowingFlame, SkillA.Attack.lv(3), null, SkillC.SpurAtk.lv(3))
        createItem(Name.リリーナ3, 1, WeaponType.RTOME, MoveType.INFANTRY, 4, 16, 9, 6, 4, 9, 5, 9, 5, 3, 6,
                Rtome.Forblaze, null, Special.GrowingFlame, SkillA.Attack.lv(3), null, SkillC.SpurAtk.lv(3), RefinedWeapon.Forblaze)
        createItem(Name.レイ, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 17, 8, 7, 5, 7, 5, 7, 6, 4, 6,
                Rtome.Rauorwolf2, Assist.RallyAttack, null, SkillA.Hp.lv(3), SkillB.SealRes.lv(3), null)
        createItem(Name.レオン, 1, WeaponType.RTOME, MoveType.CAVALRY, 5, 17, 7, 5, 6, 8, 6, 6, 4, 5, 6,
                Rtome.Brynhildr, null, Special.BlazingLight, null, SkillB.QuickRiposte.lv(3), SkillC.SavageBlow.lv(3))
        createItem(Name.レオン3, 1, WeaponType.RTOME, MoveType.CAVALRY, 5, 17, 7, 5, 6, 8, 6, 6, 4, 5, 6,
                Rtome.Brynhildr, null, Special.BlazingLight, null, SkillB.QuickRiposte.lv(3), SkillC.SavageBlow.lv(3), RefinedWeapon.Brynhildr)
        createItem(Name.レオン__夏_, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 18, 8, 5, 6, 7, 5, 7, 6, 1, 9,
                Rtome.TomatoTome2, null, Special.Iceberg, null, SkillB.SealRes.lv(3), SkillC.AtkPloy.lv(3))
        createItem(Name.チキ__大人_, 1, WeaponType.DRAGON, MoveType.INFANTRY, 3, 18, 7, 6, 9, 7, 6, 9, 4, 8, 4,
                Breath.LightningBreath2, null, Special.Bonfire, SkillA.DefiantAtk.lv(3), null, SkillC.SpurRes.lv(3))
        createItem(Name.チキ__大人_2, 1, WeaponType.DRAGON, MoveType.INFANTRY, 3, 18, 7, 6, 9, 7, 6, 9, 4, 8, 4,
                Breath.BreathOfFog, null, Special.Bonfire, SkillA.DefiantAtk.lv(3), null, SkillC.SpurRes.lv(3))
        createItem(Name.チキ__大人_3, 1, WeaponType.DRAGON, MoveType.INFANTRY, 3, 18, 7, 6, 9, 7, 6, 9, 4, 8, 4,
                Breath.BreathOfFog, null, Special.Bonfire, SkillA.DefiantAtk.lv(3), null, SkillC.SpurRes.lv(3), RefinedWeapon.BreathOfFog)
        createItem(Name.チキ__幼_, 1, WeaponType.DRAGON, MoveType.INFANTRY, 5, 15, 5, 4, 8, 7, 8, 8, 8, 7, 6,
                Breath.Flametongue2, null, Special.GrowingFlame, SkillA.ArmoredBlow.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.チキ__幼_2, 1, WeaponType.DRAGON, MoveType.INFANTRY, 5, 15, 5, 4, 8, 7, 8, 8, 8, 7, 6,
                Breath.BreathOfFog, null, Special.GrowingFlame, SkillA.ArmoredBlow.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.チキ__幼_3, 1, WeaponType.DRAGON, MoveType.INFANTRY, 5, 15, 5, 4, 8, 7, 8, 8, 8, 7, 6,
                Breath.BreathOfFog, null, Special.GrowingFlame, SkillA.ArmoredBlow.lv(3), null, SkillC.BreathOfLife.lv(3), RefinedWeapon.BreathOfFog)
        createItem(Name.アクア, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 17, 5, 7, 4, 6, 5, 8, 8, 4, 6,
                Lance.SapphireLance2, Assist.Sing, null, SkillA.Speed.lv(3), null, SkillC.FortifyRes.lv(3))
        createItem(Name.アベル, 2, WeaponType.LANCE, MoveType.CAVALRY, 4, 17, 7, 8, 8, 6, 6, 8, 7, 4, 5,
                Lance.BraveLance2, null, Special.Aegis, SkillA.Hp.lv(3), SkillB.SwordBreaker.lv(3), null)
        createItem(Name.ヴァルター, 2, WeaponType.LANCE, MoveType.FLIER, 3, 18, 8, 9, 8, 4, 7, 7, 6, 8, 3,
                Lance.CursedLance, null, Special.Luna, SkillA.DartingBlow.lv(3), null, SkillC.PanicPloy.lv(3))
        createItem(Name.ウェンディ, 2, WeaponType.LANCE, MoveType.ARMORED, 3, 23, 8, 5, 12, 6, 8, 6, 5, 8, 6,
                Lance.KillerLance2, null, Special.Escutcheon, null, SkillB.DragBack, SkillC.HoneArmor)
        createItem(Name.エスト, 2, WeaponType.LANCE, MoveType.FLIER, 3, 17, 9, 8, 5, 8, 5, 8, 6, 5, 7,
                Lance.HeavySpear2, Assist.Shove, null, SkillA.DefiantRes.lv(3), SkillB.SealSpd.lv(3), null)
        createItem(Name.エスト2, 2, WeaponType.LANCE, MoveType.FLIER, 3, 17, 9, 8, 5, 8, 5, 8, 6, 5, 7,
                Lance.WhitewingSpear, Assist.Shove, null, SkillA.DefiantRes.lv(3), SkillB.SealSpd.lv(3), null)
        createItem(Name.エスト3, 2, WeaponType.LANCE, MoveType.FLIER, 3, 17, 9, 8, 5, 8, 5, 8, 6, 5, 7,
                Lance.WhitewingSpear, Assist.Shove, null, SkillA.DefiantRes.lv(3), SkillB.SealSpd.lv(3), null,RefinedWeapon.WhitewingSpear)
        createItem(Name.エフラム, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 19, 9, 6, 8, 5, 8, 8, 5, 7, 3,
                Lance.Siegmund, null, Special.Moonbow, null, SkillB.SealDef.lv(3), SkillC.ThreatenDef.lv(3))
        createItem(Name.エフラム3, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 19, 9, 6, 8, 5, 8, 8, 5, 7, 3,
                Lance.Siegmund, null, Special.Moonbow, null, SkillB.SealDef.lv(3), SkillC.ThreatenDef.lv(3), RefinedWeapon.Siegmund)
        createItem(Name.エルフィ, 2, WeaponType.LANCE, MoveType.ARMORED, 4, 22, 12, 5, 11, 4, 9, 9, 4, 6, 5,
                Lance.SilverLance2, Assist.Smite, null, SkillA.DeathBlow.lv(3), SkillB.WaryFighter.lv(3), null)
        createItem(Name.オスカー, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 18, 7, 8, 7, 6, 6, 8, 8, 5, 3,
                Lance.SapphireLance2, Assist.RallySpdDef, null, null, SkillB.LanceBreaker.lv(3), SkillC.SpurSpdDef.lv(2))
        createItem(Name.オボロ, 2, WeaponType.LANCE, MoveType.INFANTRY, 3, 18, 8, 7, 9, 5, 6, 7, 5, 8, 5,
                Lance.HeavySpear2, Assist.RallyDefense, null, null, SkillB.SealDef.lv(3), SkillC.ThreatenRes.lv(3))
        createItem(Name.カチュア, 2, WeaponType.LANCE, MoveType.FLIER, 4, 17, 7, 10, 7, 6, 6, 7, 7, 6, 5,
                Lance.KillerLance2, null, Special.Luna, SkillA.ArmoredBlow.lv(3), SkillB.SealAtk.lv(3), null)
        createItem(Name.カチュア2, 2, WeaponType.LANCE, MoveType.FLIER, 4, 17, 7, 10, 7, 6, 6, 7, 7, 6, 5,
                Lance.WhitewingLance, null, Special.Luna, SkillA.ArmoredBlow.lv(3), SkillB.SealAtk.lv(3), null)
        createItem(Name.カチュア3, 2, WeaponType.LANCE, MoveType.FLIER, 4, 17, 7, 10, 7, 6, 6, 7, 7, 6, 5,
                Lance.WhitewingLance, null, Special.Luna, SkillA.ArmoredBlow.lv(3), SkillB.SealAtk.lv(3), null,RefinedWeapon.WhitewingLance)
        createItem(Name.カミュ, 2, WeaponType.LANCE, MoveType.CAVALRY, 3, 18, 8, 9, 7, 4, 7, 7, 7, 7, 2,
                Lance.Gradivus, null, Special.GrowingThunder, SkillA.GranisShield, null, SkillC.GoadCavalry)
        createItem(Name.カムイ__女_, 2, WeaponType.DRAGON, MoveType.INFANTRY, 3, 19, 8, 6, 8, 6, 6, 5, 9, 8, 3,
                Breath.DarkBreath2, null, Special.DraconicAura, null, SkillB.SealRes.lv(3), SkillC.HoneAtk.lv(3))
        createItem(Name.クレア, 2, WeaponType.LANCE, MoveType.FLIER, 5, 18, 7, 8, 5, 9, 5, 5, 9, 5, 7,
                Lance.SilverLance2, Assist.HarshCommand, null, null, SkillB.HitAndRun, SkillC.SpurSpd.lv(3))
        createItem(Name.クレア2, 2, WeaponType.LANCE, MoveType.FLIER, 5, 18, 7, 8, 5, 9, 5, 5, 9, 5, 7,
                Lance.Rhomphaia, Assist.HarshCommand, null, null, SkillB.HitAndRun, SkillC.SpurSpd.lv(3))
        createItem(Name.クレア3, 2, WeaponType.LANCE, MoveType.FLIER, 5, 18, 7, 8, 5, 9, 5, 5, 9, 5, 7,
                Lance.Rhomphaia, Assist.HarshCommand, null, null, SkillB.HitAndRun, SkillC.SpurSpd.lv(3), RefinedWeapon.Rhomphaia)
        createItem(Name.クレーベ, 2, WeaponType.LANCE, MoveType.CAVALRY, 4, 19, 9, 6, 8, 4, 8, 7, 5, 7, 3,
                Lance.SilverLance2, null, Special.Escutcheon, SkillA.Defense.lv(3), SkillB.HitAndRun, null)
        createItem(Name.シーダ__花嫁_, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 7, 9, 4, 8, 4, 6, 9, 3, 6,
                Btome.BlessedBouquet2, null, Special.Iceberg, SkillA.AtkRes.lv(2), null, SkillC.HoneSpd.lv(3))
        createItem(Name.ジェイガン, 2, WeaponType.LANCE, MoveType.CAVALRY, 3, 20, 8, 7, 8, 11, 4, 5, 4, 4, 7,
                Lance.SilverLance2, null, Special.Aegis, SkillA.Fury.lv(3), null, SkillC.FortifyCavalry)
        createItem(Name.シャーロッテ__花嫁_, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 20, 10, 8, 5, 4, 8, 8, 7, 5, 3,
                Lance.FirstBite2, Assist.Smite, null, SkillA.WindBoost.lv(3), null, SkillC.ThreatenAtk.lv(3))
        createItem(Name.シャニー, 2, WeaponType.LANCE, MoveType.FLIER, 3, 17, 8, 9, 6, 7, 6, 6, 8, 5, 6,
                Lance.KillerLance2, null, Special.Iceberg, null, SkillB.Desperation.lv(3), SkillC.ThreatenSpd.lv(3))
        createItem(Name.シャニー2, 2, WeaponType.LANCE, MoveType.FLIER, 3, 17, 8, 9, 6, 7, 6, 6, 8, 5, 6,
                Lance.ShannasLance, null, Special.Iceberg, null, SkillB.Desperation.lv(3), SkillC.ThreatenSpd.lv(3))
        createItem(Name.シャニー3, 2, WeaponType.LANCE, MoveType.FLIER, 3, 17, 8, 9, 6, 7, 6, 6, 8, 5, 6,
                Lance.ShannasLance, null, Special.Iceberg, null, SkillB.Desperation.lv(3), SkillC.ThreatenSpd.lv(3), RefinedWeapon.ShannasLance)
        createItem(Name.シャロン, 2, WeaponType.LANCE, MoveType.INFANTRY, 2, 19, 8, 8, 7, 5, 7, 7, 7, 6, 4,
                Lance.Fensalir, Assist.RallyAttack, null, SkillA.Speed.lv(3), null, SkillC.FortifyDef.lv(3))
        createItem(Name.シャロン3, 2, WeaponType.LANCE, MoveType.INFANTRY, 2, 19, 8, 8, 7, 5, 7, 7, 7, 6, 4,
                Lance.Fensalir, Assist.RallyAttack, null, SkillA.Speed.lv(3), null, SkillC.FortifyDef.lv(3), RefinedWeapon.Fensalir)
        createItem(Name.ソワレ, 2, WeaponType.LANCE, MoveType.CAVALRY, 3, 18, 7, 8, 7, 6, 7, 5, 8, 4, 6,
                Lance.SapphireLance2, Assist.DrawBack, null, null, SkillB.SwordBreaker.lv(3), SkillC.SpurDef.lv(3))
        createItem(Name.ターナ, 2, WeaponType.LANCE, MoveType.FLIER, 5, 17, 8, 10, 6, 6, 5, 8, 8, 5, 5,
                Lance.Vidofinir, null, Special.Moonbow, SkillA.SpdDef.lv(2), null, SkillC.Guidance.lv(3))
        createItem(Name.ターナ3, 2, WeaponType.LANCE, MoveType.FLIER, 5, 17, 8, 10, 6, 6, 5, 8, 8, 5, 5,
                Lance.Vidofinir, null, Special.Moonbow, SkillA.SpdDef.lv(2), null, SkillC.Guidance.lv(3), RefinedWeapon.Vidofnir2)
        createItem(Name.ツバキ, 2, WeaponType.LANCE, MoveType.FLIER, 3, 18, 6, 9, 9, 5, 6, 5, 8, 8, 4,
                Lance.SapphireLance2, Assist.Swap, null, SkillA.Resistance.lv(3), SkillB.QuickRiposte.lv(3), null)
        createItem(Name.ティアモ, 2, WeaponType.LANCE, MoveType.FLIER, 4, 18, 9, 9, 5, 6, 6, 8, 8, 4, 5,
                Lance.BraveLance2, null, Special.Galeforce, SkillA.TriangleAdept.lv(3), SkillB.Pass.lv(3), null)
        createItem(Name.ドニ, 2, WeaponType.LANCE, MoveType.INFANTRY, 3, 17, 7, 5, 6, 4, 8, 9, 7, 8, 5,
                Lance.BraveLance2, Assist.ReciprocalAid, null, SkillA.Hp.lv(3), SkillB.DragBack, null)
        createItem(Name.ネフェニー, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 18, 7, 9, 8, 5, 5, 7, 8, 8, 3,
                Lance.SlayingLance2, null, Special.Moonbow, SkillA.AtkSpd.lv(2), SkillB.Wrath.lv(3), null)
        createItem(Name.ネフェニー2, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 18, 7, 9, 8, 5, 5, 7, 8, 8, 3,
                Lance.DauntlessLance, null, Special.Moonbow, SkillA.AtkSpd.lv(2), SkillB.Wrath.lv(3), null)
        createItem(Name.ネフェニー3, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 18, 7, 9, 8, 5, 5, 7, 8, 8, 3,
                Lance.DauntlessLance, null, Special.Moonbow, SkillA.AtkSpd.lv(2), SkillB.Wrath.lv(3), null, RefinedWeapon.DauntlessLance)
        createItem(Name.ピエリ, 2, WeaponType.LANCE, MoveType.CAVALRY, 4, 16, 9, 9, 6, 6, 5, 7, 7, 4, 7,
                Lance.KillerLance2, null, Special.Glimmer, SkillA.Resistance.lv(3), null, SkillC.ThreatenDef.lv(3))
        createItem(Name.ヒノカ, 2, WeaponType.LANCE, MoveType.FLIER, 5, 19, 7, 8, 6, 7, 6, 9, 7, 5, 4,
                Lance.BraveLance2, null, Special.BlazingWind, SkillA.DefiantDef.lv(3), null, SkillC.HoneFliers)
        createItem(Name.ヒノカ2, 2, WeaponType.LANCE, MoveType.FLIER, 5, 19, 7, 8, 6, 7, g50, g65, g55, g45, g40,
                Lance.HinokasSpear, null, Special.BlazingWind, SkillA.DefiantDef.lv(3), null, SkillC.HoneFliers)
        createItem(Name.ヒノカ3, 2, WeaponType.LANCE, MoveType.FLIER, 5, 19, 7, 8, 6, 7, g50, g65, g55, g45, g40,
                Lance.HinokasSpear, null, Special.BlazingWind, SkillA.DefiantDef.lv(3), null, SkillC.HoneFliers, RefinedWeapon.HinokasSpear)
        createItem(Name.フロリーナ, 2, WeaponType.LANCE, MoveType.FLIER, 3, 18, 7, 8, 6, 8, 7, 6, 5, 5, 8,
                Lance.HeavySpear2, Assist.ArdentSacrifice, null, SkillA.DartingBlow.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.フロリーナ2, 2, WeaponType.LANCE, MoveType.FLIER, 3, 18, 7, 8, 6, 8, 7, 6, 5, 5, 8,
                Lance.FlorinasLance, Assist.ArdentSacrifice, null, SkillA.DartingBlow.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.フロリーナ3, 2, WeaponType.LANCE, MoveType.FLIER, 3, 18, 7, 8, 6, 8, 7, 6, 5, 5, 8,
                Lance.FlorinasLance, Assist.ArdentSacrifice, null, SkillA.DartingBlow.lv(3), null, SkillC.BreathOfLife.lv(3), RefinedWeapon.FlorinasLance)
        createItem(Name.ベルクト, 2, WeaponType.LANCE, MoveType.CAVALRY, 3, 19, 8, 5, 7, 7, 7, 8, 4, 7, 4,
                Lance.BerkutsLance2, null, Special.BlazingFlame, SkillA.WaterBoost.lv(3), null, SkillC.WardCavalry)
        createItem(Name.マークス__春_, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 18, 6, 6, 9, 7, 6, 5, 6, 8, 5,
                Lance.CarrotLance2, Assist.Swap, null, null, SkillB.LiveForHonor, SkillC.FortifyDef.lv(3))
        createItem(Name.マチルダ, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 16, 7, 8, 7, 8, 5, 6, 7, 4, 8,
                Lance.Ridersbane2, Assist.RallyResistance, null, null, SkillB.CancelAffinity.lv(3), SkillC.HoneAtk.lv(3))
        createItem(Name.ルカ, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 19, 9, 5, 10, 4, 8, 8, 4, 9, 2,
                Lance.KillerLance2, null, Special.SacredCowl, SkillA.FortressDef.lv(3), SkillB.Obstruct.lv(3), null)
        createItem(Name.ルキナ__春_, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 7, 10, 5, 6, 5, 6, 8, 4, 5,
                Btome.BlueEgg2, Assist.RallySpeed, null, SkillA.SwiftSparrow.lv(2), SkillB.SealRes.lv(3), null)
        createItem(Name.ルキナ__総選挙_, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 17, 8, 10, 8, 4, 7, 8, 8, 5, 3,
                Lance.Geirskogul, null, Special.Aether, SkillA.SturdyBlow.lv(2), null, SkillC.DriveSpd.lv(2))
        createItem(Name.ルフレ__夏_, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 18, 8, 8, 6, 7, 4, 7, 8, 6, 6,
                Lance.DeftHarpoon2, Assist.Reposition, null, SkillA.HpDef.lv(2), null, SkillC.LanceValor.lv(3))
        createItem(Name.ロディ, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 18, 7, 8, 6, 7, 6, 7, 8, 5, 4,
                Lance.FiresweepLance2, Assist.RallyDefRes, null, null, SkillB.DragBack, SkillC.DriveDef.lv(2))
        createItem(Name.ウルスラ, 2, WeaponType.BTOME, MoveType.CAVALRY, 3, 16, 7, 8, 4, 8, 5, 6, 7, 3, 6,
                Btome.Blarwolf2, null, Special.GrowingThunder, SkillA.DeathBlow.lv(3), null, SkillC.ThreatenRes.lv(3))
        createItem(Name.オーディン, 2, WeaponType.BTOME, MoveType.INFANTRY, 3, 19, 5, 8, 6, 6, 7, 4, 7, 5, 5,
                Btome.Blarblade2, null, Special.Moonbow, SkillA.DefiantAtk.lv(3), SkillB.RTomeBreaker.lv(3), null)
        createItem(Name.オーディン2, 2, WeaponType.BTOME, MoveType.INFANTRY, 3, 19, 5, 8, 6, 6, 7, 4, 7, 5, 5,
                Btome.OdinsGrimoire, null, Special.Moonbow, SkillA.DefiantAtk.lv(3), SkillB.RTomeBreaker.lv(3), null)
        createItem(Name.オーディン3, 2, WeaponType.BTOME, MoveType.INFANTRY, 3, 19, 5, 8, 6, 6, 7, 4, 7, 5, 5,
                Btome.OdinsGrimoire, null, Special.Moonbow, SkillA.DefiantAtk.lv(3), SkillB.RTomeBreaker.lv(3), null, RefinedWeapon.OdinsGrimoire)
        createItem(Name.オルエン, 2, WeaponType.BTOME, MoveType.CAVALRY, 5, 17, 7, 8, 5, 6, 4, 5, 8, 3, 7,
                Btome.DireThunder, Assist.Reposition, null, SkillA.WardingBlow.lv(3), null, SkillC.WardCavalry)
        createItem(Name.カムイ__夏_, 2, WeaponType.BTOME, MoveType.FLIER, 5, 17, 7, 8, 5, 7, 4, 7, 8, 4, 5,
                Btome.SealifeTome2, null, Special.DragonFang, SkillA.SwiftStrike.lv(2), null, SkillC.FortifyFliers)
        createItem(Name.シグレ__舞踏祭_, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 15, 7, 5, 4, 5, 5, 7, 7, 4, 5,
                Btome.DancersScore2, Assist.Sing, null, null, SkillB.GeyserDance.lv(2), SkillC.BTomeValor.lv(3))
        createItem(Name.ティルテュ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 17, 8, 9, 4, 6, 6, 7, 8, 2, 5,
                Btome.Blarblade2, Assist.RallySpdRes, null, SkillA.AtkRes.lv(2), null, SkillC.DriveSpd.lv(2))
        createItem(Name.デューテ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 10, 8, 3, 7, 4, 8, 8, 1, 7,
                Btome.DarkAura, null, Special.Miracle, SkillA.DeathBlow.lv(3), null, SkillC.DriveAtk.lv(2))
        createItem(Name.メイ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 10, 7, 3, 8, 5, 8, 7, 2, 6,
                Btome.Blarowl2, Assist.DrawBack, null, null, SkillB.Desperation.lv(3), SkillC.BTomeExperience.lv(3))
        createItem(Name.ラインハルト, 2, WeaponType.BTOME, MoveType.CAVALRY, 4, 16, 8, 6, 5, 8, 6, 7, 4, 6, 4,
                Btome.DireThunder, null, Special.BlazingThunder, null, SkillB.Vantage.lv(3), SkillC.GoadCavalry)
        createItem(Name.リンダ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 9, 10, 4, 5, 5, 8, 8, 1, 6,
                Btome.Aura, Assist.ArdentSacrifice, null, SkillA.Speed.lv(3), null, SkillC.FortifyRes.lv(3))
        createItem(Name.リンダ3, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 9, 10, 4, 5, 5, 8, 8, 1, 6,
                Btome.Aura, Assist.ArdentSacrifice, null, SkillA.Speed.lv(3), null, SkillC.FortifyRes.lv(3), RefinedWeapon.Aura)
        createItem(Name.ルフレ__男_, 2, WeaponType.BTOME, MoveType.INFANTRY, 3, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Btome.Blarraven2, null, Special.Bonfire, SkillA.DefiantSpd.lv(3), null, SkillC.SpurDef.lv(3))
        createItem(Name.ルフレ__男_2, 2, WeaponType.BTOME, MoveType.INFANTRY, 3, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Btome.TacticalBolt, null, Special.Bonfire, SkillA.DefiantSpd.lv(3), null, SkillC.SpurDef.lv(3))
        createItem(Name.ルフレ__男_3, 2, WeaponType.BTOME, MoveType.INFANTRY, 3, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Btome.TacticalBolt, null, Special.Bonfire, SkillA.DefiantSpd.lv(3), null, SkillC.SpurDef.lv(3),RefinedWeapon.TacticalBolt)
        createItem(Name.ニニアン, 2, WeaponType.DRAGON, MoveType.INFANTRY, 5, 16, 5, 7, 6, 5, 8, 5, 8, 4, 6,
                Breath.LightBreath2, Assist.Dance, null, null, SkillB.EscapeRoute.lv(3), SkillC.FortifyDragons)
        createItem(Name.ノノ, 2, WeaponType.DRAGON, MoveType.INFANTRY, 4, 17, 6, 5, 6, 5, 9, 9, 6, 7, 6,
                Breath.LightningBreath2, Assist.RallyDefense, null, SkillA.Defense.lv(3), null, SkillC.ThreatenRes.lv(3))
        createItem(Name.アイク__総選挙_, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 17, 10, 6, 9, 5, 8, 8, 6, 8, 3,
                Axe.Urvan, null, Special.Aether, SkillA.SteadyBreath, SkillB.BeorcsBlessing, SkillC.ThreatenDef.lv(3))
        createItem(Name.アクア__舞踏祭_, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 16, 6, 8, 3, 6, 5, 8, 8, 4, 6,
                Axe.Uror, Assist.Sing, null, SkillA.TriangleAdept.lv(3), null, SkillC.DriveRes.lv(2))
        createItem(Name.アメリア, 3, WeaponType.AXE, MoveType.ARMORED, 5, 19, 6, 8, 9, 4, 9, 9, 8, 8, 5,
                Axe.SlayingAxe2, null, Special.HolyVestments, SkillA.EarthBoost.lv(3), null, SkillC.ArmorMarch.lv(3))
        createItem(Name.アンナ, 3, WeaponType.AXE, MoveType.INFANTRY, 2, 19, 7, 10, 5, 6, 6, 6, 9, 4, 6,
                Axe.Noatun, null, Special.Astra, null, SkillB.Vantage.lv(3), SkillC.SpurRes.lv(3))
        createItem(Name.アンナ3, 3, WeaponType.AXE, MoveType.INFANTRY, 2, 19, 7, 10, 5, 6, 6, 6, 9, 4, 6,
                Axe.Noatun, null, Special.Astra, null, SkillB.Vantage.lv(3), SkillC.SpurRes.lv(3), RefinedWeapon.Noatun)
        createItem(Name.カミラ, 3, WeaponType.AXE, MoveType.FLIER, 4, 18, 8, 8, 6, 7, 5, 6, 7, 6, 7,
                Axe.BraveAxe2, null, Special.DraconicAura, SkillA.DartingBlow.lv(3), null, SkillC.SavageBlow.lv(3))
        createItem(Name.カミラ2, 3, WeaponType.AXE, MoveType.FLIER, 4, 18, 8, 8, 6, 7, 5, 6, 7, 6, 7,
                Axe.CamillasAxe, null, Special.DraconicAura, SkillA.DartingBlow.lv(3), null, SkillC.SavageBlow.lv(3))
        createItem(Name.カミラ3, 3, WeaponType.AXE, MoveType.FLIER, 4, 18, 8, 8, 6, 7, 5, 6, 7, 6, 7,
                Axe.CamillasAxe, null, Special.DraconicAura, SkillA.DartingBlow.lv(3), null, SkillC.SavageBlow.lv(3), RefinedWeapon.CamillasAxe)
        createItem(Name.ギュンター, 3, WeaponType.AXE, MoveType.CAVALRY, 3, 21, 10, 7, 11, 5, 6, 6, 4, 6, 2,
                Axe.SilverAxe2, Assist.HarshCommand, null, SkillA.ArmoredBlow.lv(3), null, SkillC.HoneCavalry)
        createItem(Name.クロム__春_, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 19, 9, 8, 6, 5, 7, 8, 7, 6, 3,
                Axe.CarrotAxe2, Assist.Shove, null, SkillA.AtkDef.lv(2), null, SkillC.AxeExperience.lv(3))
        createItem(Name.シーマ, 3, WeaponType.AXE, MoveType.ARMORED, 4, 21, 8, 6, 12, 7, 7, 6, 5, 7, 8,
                Axe.KillerAxe2, null, Special.Escutcheon, SkillA.SvalinnShield, null, SkillC.FortifyArmor)
        createItem(Name.セルジュ, 3, WeaponType.AXE, MoveType.FLIER, 3, 20, 10, 6, 8, 3, 8, 9, 5, 7, 2,
                Axe.Hammer2, Assist.Pivot, null, SkillA.Attack.lv(3), null, SkillC.FortifyDef.lv(3))
        createItem(Name.セルジュ2, 3, WeaponType.AXE, MoveType.FLIER, 3, 20, 10, 6, 8, 3, 8, 9, 5, 7, 2,
                Axe.CherchesAxe, Assist.Pivot, null, SkillA.Attack.lv(3), null, SkillC.FortifyDef.lv(3))
        createItem(Name.セルジュ3, 3, WeaponType.AXE, MoveType.FLIER, 3, 20, 10, 6, 8, 3, 8, 9, 5, 7, 2,
                Axe.CherchesAxe, Assist.Pivot, null, SkillA.Attack.lv(3), null, SkillC.FortifyDef.lv(3), RefinedWeapon.CherchesAxe)
        createItem(Name.チキ__夏_, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 18, 8, 6, 8, 7, 4, 9, 7, 7, 4,
                Axe.MelonCrusher2, null, Special.Sol, SkillA.CloseDef.lv(3), null, SkillC.AxeValor.lv(3))
        createItem(Name.ティアマト, 3, WeaponType.AXE, MoveType.CAVALRY, 5, 18, 6, 8, 6, 8, 5, 6, 8, 5, 6,
                Axe.EmeraldAxe2, Assist.ReciprocalAid, null, SkillA.ArmoredBlow.lv(3), SkillB.Guard.lv(3), null)
        createItem(Name.ティアマト2, 3, WeaponType.AXE, MoveType.CAVALRY, 5, 18, 6, 8, 6, 8, 5, 6, 8, 5, 6,
                Axe.DraconicPoleax, Assist.ReciprocalAid, null, SkillA.ArmoredBlow.lv(3), SkillB.Guard.lv(3), null)
        createItem(Name.ティアマト3, 3, WeaponType.AXE, MoveType.CAVALRY, 5, 18, 6, 8, 6, 8, 5, 6, 8, 5, 6,
                Axe.DraconicPoleax, Assist.ReciprocalAid, null, SkillA.ArmoredBlow.lv(3), SkillB.Guard.lv(3), null, RefinedWeapon.DraconicPoleax)
        createItem(Name.ナーシェン, 3, WeaponType.AXE, MoveType.FLIER, 2, 18, 7, 7, 8, 7, 7, 6, 6, 7, 5,
                Axe.EmeraldAxe2, null, Special.Vengeance, null, SkillB.LanceBreaker.lv(3), SkillC.SavageBlow.lv(3))
        createItem(Name.ニノ, 3, WeaponType.GTOME, MoveType.INFANTRY, 3, 16, 7, 10, 4, 7, 4, 8, 8, 3, 5,
                Gtome.Gronnblade2, Assist.DrawBack, null, SkillA.Resistance.lv(3), null, SkillC.HoneAtk.lv(3))
        createItem(Name.バーツ, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 20, 9, 8, 6, 4, 8, 7, 7, 7, 2,
                Axe.BraveAxe2, Assist.Reposition, null, null, SkillB.KnockBack, SkillC.SpurAtk.lv(3))
        createItem(Name.バアトル, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 21, 10, 6, 7, 3, 9, 8, 5, 8, 1,
                Axe.Hammer2, Assist.Smite, null, SkillA.Fury.lv(3), SkillB.BrashAssault.lv(3), null)
        createItem(Name.バアトル2, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 21, 10, 6, 7, 3, 9, 8, 5, 8, 1,
                Axe.AxeOfVirility, Assist.Smite, null, SkillA.Fury.lv(3), SkillB.BrashAssault.lv(3), null)
        createItem(Name.バアトル3, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 21, 10, 6, 7, 3, 9, 8, 5, 8, 1,
                Axe.AxeOfVirility, Assist.Smite, null, SkillA.Fury.lv(3), SkillB.BrashAssault.lv(3), null, RefinedWeapon.AxeOfVirility)
        createItem(Name.ハロルド, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 19, 8, 7, 8, 5, 7, 7, 6, 6, 5,
                Axe.EmeraldAxe2, Assist.Swap, null, SkillA.Hp.lv(3), SkillB.LanceBreaker.lv(3), null)
        createItem(Name.ファ, 3, WeaponType.DRAGON, MoveType.INFANTRY, 4, 16, 5, 4, 6, 8, 10, 9, 7, 5, 6,
                Breath.LightBreath2, Assist.DrawBack, null, null, SkillB.Renewal.lv(3), SkillC.ThreatenAtk.lv(3))
        createItem(Name.フレデリク, 3, WeaponType.AXE, MoveType.CAVALRY, 3, 19, 9, 6, 8, 4, 7, 8, 5, 9, 1,
                Axe.Hammer2, null, Special.Luna, null, SkillB.WingsOfMercy.lv(3), SkillC.FortifyDef.lv(3))
        createItem(Name.ヘクトル, 3, WeaponType.AXE, MoveType.ARMORED, 5, 24, 10, 5, 11, 4, 9, 8, 5, 8, 3,
                Axe.Armoads, null, Special.Pavise, SkillA.DistantCounter, null, SkillC.GoadArmor)
        createItem(Name.ベルカ, 3, WeaponType.AXE, MoveType.FLIER, 3, 20, 7, 6, 9, 5, 8, 6, 4, 9, 4,
                Axe.KillerAxe2, null, Special.Glimmer, SkillA.DefiantDef.lv(3), SkillB.Lunge, null)
        createItem(Name.ホークアイ, 3, WeaponType.AXE, MoveType.INFANTRY, 4, 21, 9, 5, 6, 6, 7, 7, 4, 6, 7,
                Axe.KillerAxe2, null, Special.GrowingLight, SkillA.DeathBlow.lv(3), null, SkillC.ThreatenAtk.lv(3))
        createItem(Name.マークス__夏_, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 19, 8, 6, 8, 6, 7, 7, 7, 9, 1,
                Axe.LilithFloatie2, null, Special.Bonfire, SkillA.FireBoost.lv(3), null, SkillC.InfantryPulse.lv(3))
        createItem(Name.ミシェイル, 3, WeaponType.AXE, MoveType.FLIER, 3, 19, 8, 7, 9, 4, 7, 8, 5, 8, 3,
                Axe.Hauteclere, null, Special.BlazingThunder, SkillA.IotesShield, null, SkillC.ThreatenDef.lv(3))
        createItem(Name.ミシェイル3, 3, WeaponType.AXE, MoveType.FLIER, 3, 19, 8, 7, 9, 4, 7, 8, 5, 8, 3,
                Axe.Hauteclere, null, Special.BlazingThunder, SkillA.IotesShield, null, SkillC.ThreatenDef.lv(3), RefinedWeapon.Hauteclere)
        createItem(Name.ミネルバ, 3, WeaponType.AXE, MoveType.FLIER, 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Axe.Hauteclere, null, Special.SacredCowl, SkillA.LifeAndDeath.lv(3), null, SkillC.WardFliers)
        createItem(Name.ミネルバ3, 3, WeaponType.AXE, MoveType.FLIER, 5, 18, 7, 9, 8, 5, 6, 7, 7, 7, 4,
                Axe.Hauteclere, null, Special.SacredCowl, SkillA.LifeAndDeath.lv(3), null, SkillC.WardFliers, RefinedWeapon.Hauteclere)
        createItem(Name.レイヴァン, 3, WeaponType.AXE, MoveType.INFANTRY, 4, 19, 8, 9, 6, 5, 6, 8, 8, 5, 4,
                Axe.BraveAxe2, null, Special.Sol, SkillA.DefiantSpd.lv(3), null, SkillC.ThreatenDef.lv(3))
        createItem(Name.レイヴァン2, 3, WeaponType.AXE, MoveType.INFANTRY, 4, 19, 8, 9, 6, 5, 6, 8, 8, 5, 4,
                Axe.Basilikos, null, Special.Sol, SkillA.DefiantSpd.lv(3), null, SkillC.ThreatenDef.lv(3))
        createItem(Name.レイヴァン3, 3, WeaponType.AXE, MoveType.INFANTRY, 4, 19, 8, 9, 6, 5, 6, 8, 8, 5, 4,
                Axe.Basilikos, null, Special.Sol, SkillA.DefiantSpd.lv(3), null, SkillC.ThreatenDef.lv(3), RefinedWeapon.Basilikos)
        createItem(Name.ローロー, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 20, 10, 9, 5, 3, 8, 8, 8, 4, 3,
                Axe.LegionsAxe2, null, Special.Reprisal, SkillA.Fury.lv(3), SkillB.Obstruct.lv(3), null)
        createItem(Name.アズール__舞踏祭_, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 15, 6, 7, 5, 3, 6, 6, 8, 4, 4,
                Gtome.DancersRing2, Assist.Dance, null, null, SkillB.GaleDance.lv(3), SkillC.HoneAtk.lv(3))
        createItem(Name.エリーゼ__夏_, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 10, 8, 3, 6, 4, 8, 8, 3, 5,
                Gtome.HibiscusTome2, Assist.RallyAtkRes, null, SkillA.SpdRes.lv(2), null, SkillC.GTomeValor.lv(3))
        createItem(Name.カミラ__春_, 3, WeaponType.GTOME, MoveType.FLIER, 5, 17, 9, 6, 8, 4, 6, 8, 5, 6, 3,
                Gtome.GreenEgg2, Assist.RallyAttack, null, SkillA.DefiantSpd.lv(3), SkillB.LiveForBounty, null)
        createItem(Name.セシリア, 3, WeaponType.GTOME, MoveType.CAVALRY, 3, 17, 8, 6, 5, 7, 5, 7, 5, 4, 6,
                Gtome.Gronnraven2, Assist.RallyResistance, null, SkillA.Attack.lv(3), SkillB.EscapeRoute.lv(3), null)
        createItem(Name.セネリオ, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 7, 9, 4, 7, g45, g60, g55, g30, g50,
                Gtome.Rexcalibur2, null, Special.GrowingWind, null, SkillB.Watersweep.lv(3), SkillC.FortifyRes.lv(3))
        createItem(Name.セネリオ2, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 7, 9, 4, 7, 5, 8, 7, 2, 6,
                Gtome.WindsBrand, null, Special.GrowingWind, null, SkillB.Watersweep.lv(3), SkillC.FortifyRes.lv(3))
        createItem(Name.セネリオ3, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 7, 9, 4, 7, 5, 8, 7, 2, 6,
                Gtome.WindsBrand, null, Special.GrowingWind, null, SkillB.Watersweep.lv(3), SkillC.FortifyRes.lv(3), RefinedWeapon.WindsBrand)
        createItem(Name.ソニア, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 7, 7, 5, 8, 5, 8, 7, 1, 7,
                Gtome.DarkExcalibur, null, Special.Moonbow, SkillA.DeathBlow.lv(3), null, SkillC.ResPloy.lv(3))
        createItem(Name.ディアドラ, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 9, 6, 3, 9, 5, 7, 6, 2, 8,
                Gtome.DivineNaga, Assist.ArdentSacrifice, null, null, SkillB.QuickRiposte.lv(3), SkillC.SpdPloy.lv(3))
        createItem(Name.ボーイ, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 19, 7, 5, 8, 5, 7, 6, 6, 7, 2,
                Gtome.Gronnowl2, null, Special.Ignis, SkillA.EarthBoost.lv(3), SkillB.Renewal.lv(3), null)
        createItem(Name.マリク, 3, WeaponType.GTOME, MoveType.INFANTRY, 4, 19, 7, 8, 6, 4, 7, 5, 7, 6, 3,
                Gtome.Excalibur, null, Special.GrowingWind, SkillA.Hp.lv(3), null, SkillC.SpurRes.lv(3))
        createItem(Name.マリク3, 3, WeaponType.GTOME, MoveType.INFANTRY, 4, 19, 7, 8, 6, 4, 7, 5, 7, 6, 3,
                Gtome.Excalibur, null, Special.GrowingWind, SkillA.Hp.lv(3), null, SkillC.SpurRes.lv(3), RefinedWeapon.Excalibur)
        createItem(Name.ユリア, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 16, 9, 7, 4, 8, 6, 8, 5, 2, 7,
                Gtome.Naga, null, Special.DragonFang, SkillA.Resistance.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.ルフレ__女_, 3, WeaponType.GTOME, MoveType.INFANTRY, 2, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Gtome.Gronnwolf2, null, Special.Ignis, SkillA.DefiantRes.lv(3), SkillB.BTomeBreaker.lv(3), null)
        createItem(Name.ルフレ__女_2, 3, WeaponType.GTOME, MoveType.INFANTRY, 2, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Gtome.TacticalGale, null, Special.Ignis, SkillA.DefiantRes.lv(3), SkillB.BTomeBreaker.lv(3), null)
        createItem(Name.ルフレ__女_3, 3, WeaponType.GTOME, MoveType.INFANTRY, 2, 18, 7, 7, 7, 5, 6, 6, 6, 6, 4,
                Gtome.TacticalGale, null, Special.Ignis, SkillA.DefiantRes.lv(3), SkillB.BTomeBreaker.lv(3), null,RefinedWeapon.TacticalGale)
        createItem(Name.アサマ, 0, WeaponType.STAFF, MoveType.INFANTRY, 3, 19, 4, 7, 8, 6, 7, 4, 5, 7, 5,
                Staff.Pain, Assist.Martyr, Special.SolidEarthBalm, null, null, SkillC.ThreatenAtk.lv(3))
        createItem(Name.ヴィオール, 0, WeaponType.BOW, MoveType.INFANTRY, 3, 20, 7, 7, 7, 3, 8, 7, 7, 5, 1,
                Bow.SilverBow2, null, Special.Astra, SkillA.DefiantRes.lv(3), SkillB.SealSpd.lv(3), null)
        createItem(Name.エフィ, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 16, 6, 3, 4, 7, 8, 7, 6, 6, 7,
                Bow.FiresweepBow2, null, Special.Noontime, null, SkillB.WingsOfMercy.lv(3), SkillC.BowExperience.lv(3))
        createItem(Name.エリーゼ, 0, WeaponType.STAFF, MoveType.CAVALRY, 5, 15, 8, 8, 4, 8, 3, 7, 7, 3, 7,
                Staff.Gravity, Assist.Recover, Special.KindledFireBalm, null, SkillB.LiveToServe.lv(3), null)
        createItem(Name.オリヴィエ__舞踏祭_, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 15, 6, 8, 3, 4, 5, 6, 8, 2, 7,
                Dagger.DancersFan2, Assist.Dance, null, SkillA.DistantDef.lv(3), SkillB.BlazeDance.lv(3), null)
        createItem(Name.ガイア, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 18, 7, 10, 5, 4, 7, 6, 8, 4, 3,
                Dagger.RogueDagger2, Assist.RallySpeed, null, SkillA.DefiantAtk.lv(3), SkillB.Pass.lv(3), null)
        createItem(Name.ガイア__夏_, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 17, 8, 9, 4, 6, 4, 6, 9, 3, 6,
                Bow.RefreshingBolt2, null, Special.Astra, null, SkillB.Vantage.lv(3), SkillC.DefPloy.lv(3))
        createItem(Name.カゲロウ, 0, WeaponType.DAGGER, MoveType.INFANTRY, 4, 16, 9, 8, 5, 6, 3, 8, 7, 4, 6,
                Dagger.PoisonDagger2, null, Special.Reprisal, SkillA.WardingBlow.lv(3), SkillB.DaggerBreaker.lv(3), null)
        createItem(Name.クライネ, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 18, 7, 8, 6, 5, 5, 7, 8, 5, 3,
                Bow.ClarissesBow2, null, Special.Glimmer, null, SkillB.PoisonStrike.lv(3), SkillC.ThreatenDef.lv(3))
        createItem(Name.クラリーネ, 0, WeaponType.STAFF, MoveType.CAVALRY, 3, 16, 6, 9, 5, 7, 5, 5, 7, 4, 6,
                Staff.Fear, Assist.Martyr, Special.SwiftWindsBalm, SkillA.Resistance.lv(3), null, null)
        createItem(Name.クレイン, 0, WeaponType.BOW, MoveType.INFANTRY, 4, 18, 9, 7, 5, 5, 6, 6, 8, 3, 5,
                Bow.BraveBow2, null, Special.Glacies, SkillA.DeathBlow.lv(3), SkillB.QuickRiposte.lv(3), null)
        createItem(Name.ゴードン, 0, WeaponType.BOW, MoveType.INFANTRY, 3, 19, 7, 6, 8, 4, 7, 7, 5, 7, 2,
                Bow.BraveBow2, Assist.Shove, null, SkillA.Attack.lv(3), SkillB.Vantage.lv(3), null)
        createItem(Name.サイゾウ, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 17, 7, 8, 9, 3, 5, 6, 8, 7, 2,
                Dagger.SmokeDagger2, Assist.HarshCommand, null, null, SkillB.PoisonStrike.lv(3), SkillC.SpurSpd.lv(3))
        createItem(Name.サクラ, 0, WeaponType.STAFF, MoveType.INFANTRY, 4, 17, 6, 8, 5, 8, 5, 6, 6, 5, 6,
                Staff.Fear, Assist.Physic, Special.StillWaterBalm, null, null, SkillC.FortifyDef.lv(3))
        createItem(Name.ジェニー, 0, WeaponType.STAFF, MoveType.INFANTRY, 5, 17, 9, 6, 4, 8, 3, 8, 5, 4, 8,
                Staff.Gravity, Assist.Physic, Special.HeavenlyLight, null, SkillB.WrathfulStaff.lv(3), null)
        createItem(Name.ジャファル, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 17, 7, 9, 6, 5, 7, 5, 7, 5, 4,
                Dagger.DeathlyDagger, null, Special.Glimmer, SkillA.LifeAndDeath.lv(3), null, SkillC.ThreatenSpd.lv(3))
        createItem(Name.ジャファル3, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 17, 7, 9, 6, 5, 7, 5, 7, 5, 4,
                Dagger.DeathlyDagger, null, Special.Glimmer, SkillA.LifeAndDeath.lv(3), null, SkillC.ThreatenSpd.lv(3), RefinedWeapon.DeathlyDagger2)
        createItem(Name.ジョーカー, 0, WeaponType.DAGGER, MoveType.INFANTRY, 4, 17, 7, 9, 6, 5, 6, 6, 6, 5, 5,
                Dagger.SilverDagger2, Assist.RallyResistance, null, SkillA.Defense.lv(3), SkillB.Renewal.lv(3), null)
        createItem(Name.ジョルジュ, 0, WeaponType.BOW, MoveType.INFANTRY, 4, 18, 8, 8, 5, 5, 5, 7, 7, 5, 4,
                Bow.Parthia, null, Special.BlazingFlame, null, SkillB.SealAtk.lv(3), SkillC.SpurSpd.lv(3))
        createItem(Name.ジョルジュ3, 0, WeaponType.BOW, MoveType.INFANTRY, 4, 18, 8, 8, 5, 5, 5, 7, 7, 5, 4,
                Bow.Parthia, null, Special.BlazingFlame, null, SkillB.SealAtk.lv(3), SkillC.SpurSpd.lv(3), RefinedWeapon.Parthia2)
        createItem(Name.セーラ, 0, WeaponType.STAFF, MoveType.INFANTRY, 3, 16, 6, 9, 4, 9, 4, 7, 6, 4, 7,
                Staff.Absorb, Assist.Recover, Special.SwiftWindsBalm, null, null, SkillC.HoneAtk.lv(3))
        createItem(Name.セツナ, 0, WeaponType.BOW, MoveType.INFANTRY, 3, 18, 6, 9, 5, 6, 5, 6, 9, 4, 4,
                Bow.AssassinsBow2, Assist.ReciprocalAid, null, SkillA.Hp.lv(3), SkillB.BowBreaker.lv(3), null)
        createItem(Name.セツナ2, 0, WeaponType.BOW, MoveType.INFANTRY, 3, 18, 6, 9, 5, 6, 5, 6, 9, 4, 4,
                Bow.GuardBow2, Assist.ReciprocalAid, null, SkillA.Hp.lv(3), SkillB.BowBreaker.lv(3), null)
        createItem(Name.ゼロ, 0, WeaponType.BOW, MoveType.INFANTRY, 3, 18, 6, 8, 4, 8, 5, 5, 8, 2, 8,
                Bow.KillerBow2, null, Special.Iceberg, SkillA.WardingBlow.lv(3), null, SkillC.SpurRes.lv(3))
        createItem(Name.タクミ, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 18, 8, 7, 6, 5, 6, 7, 8, 5, 2,
                Bow.FujinYumi, null, Special.Vengeance, SkillA.CloseCounter, null, SkillC.ThreatenSpd.lv(3))
        createItem(Name.タクミ3, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 18, 8, 7, 6, 5, 6, 7, 8, 5, 2,
                Bow.FujinYumi, null, Special.Vengeance, SkillA.CloseCounter, null, SkillC.ThreatenSpd.lv(3), RefinedWeapon.FujinYumi)
        createItem(Name.ティアモ__花嫁_, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 17, 9, 9, 4, 5, 5, 8, 8, 3, 4,
                Bow.CupidArrow2, Assist.RallyAtkSpd, null, null, SkillB.EscapeRoute.lv(3), SkillC.BreathOfLife.lv(3))
        createItem(Name.ヒーニアス, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 16, 9, 8, 4, 7, 5, 7, 8, 1, 7,
                Bow.Nidhogg, null, Special.Iceberg, SkillA.FortressRes.lv(3), SkillB.CancelAffinity.lv(3), null)
        createItem(Name.ヒーニアス3, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 16, 9, 8, 4, 7, 5, 7, 8, 1, 7,
                Bow.Nidhogg, null, Special.Iceberg, SkillA.FortressRes.lv(3), SkillB.CancelAffinity.lv(3), null, RefinedWeapon.Nidhogg)
        createItem(Name.フェリシア, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 15, 6, 11, 3, 9, 5, 4, 8, 3, 8,
                Dagger.SilverDagger2, null, Special.Glacies, SkillA.Resistance.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.フェリシア2, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 15, 6, 11, 3, 9, 5, 4, 8, 3, 8,
                Dagger.FeliciasPlate, null, Special.Glacies, SkillA.Resistance.lv(3), null, SkillC.BreathOfLife.lv(3))
        createItem(Name.フェリシア3, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 15, 6, 11, 3, 9, 5, 4, 8, 3, 8,
                Dagger.FeliciasPlate, null, Special.Glacies, SkillA.Resistance.lv(3), null, SkillC.BreathOfLife.lv(3), RefinedWeapon.FeliciasPlate)
        createItem(Name.プリシラ, 0, WeaponType.STAFF, MoveType.CAVALRY, 4, 17, 7, 7, 4, 8, 5, 6, 6, 3, 7,
                Staff.Panic, Assist.Rehabilitate, Special.StillWaterBalm, null, null, SkillC.SpurDef.lv(3))
        createItem(Name.フレデリク__夏_, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 18, 8, 7, 6, 5, 6, 7, 7, 6, 2,
                Dagger.Seashell2, Assist.ArdentSacrifice, null, SkillA.ArmoredBlow.lv(3), SkillB.SealAtkSpd.lv(2), null)
        createItem(Name.マシュー, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 17, 6, 10, 6, 5, 7, 5, 7, 7, 2,
                Dagger.RogueDagger2, Assist.ReciprocalAid, null, null, SkillB.PoisonStrike.lv(3), SkillC.HoneSpd.lv(3))
        createItem(Name.マリア, 0, WeaponType.STAFF, MoveType.INFANTRY, 4, 17, 5, 8, 4, 10, 5, 6, 8, 3, 6,
                Staff.Panic, Assist.Physic, Special.Miracle, null, null, SkillC.FortifyRes.lv(3))
        createItem(Name.ミスト, 0, WeaponType.STAFF, MoveType.INFANTRY, 5, 17, 8, 6, 5, 8, 6, 5, 6, 3, 8,
                Staff.Slow, Assist.Recover, Special.Miracle, null, null, SkillC.SpurDefRes.lv(2))
        createItem(Name.ラケシス, 0, WeaponType.STAFF, MoveType.INFANTRY, 4, 17, 6, 8, 5, 8, 6, 8, 4, 4, 6,
                Staff.Absorb, Assist.Physic, Special.SolidEarthBalm, null, null, SkillC.SpurRes.lv(3))
        createItem(Name.リズ, 0, WeaponType.STAFF, MoveType.INFANTRY, 3, 17, 7, 6, 6, 8, 6, 5, 5, 6, 6,
                Staff.Gravity, Assist.Rehabilitate, Special.KindledFireBalm, null, SkillB.Renewal.lv(3), null)
        createItem(Name.リフ, 0, WeaponType.STAFF, MoveType.INFANTRY, 3, 18, 5, 6, 5, 10, 7, 5, 4, 4, 8,
                Staff.Slow, Assist.Rehabilitate, Special.HeavenlyLight, null, SkillB.LiveToServe.lv(3), null)
        createItem(Name.リン__花嫁_, 0, WeaponType.STAFF, MoveType.INFANTRY, 5, 17, 6, 10, 6, 5, 6, 6, 7, 4, 5,
                Staff.Candlelight, Assist.Rehabilitate, Special.SwiftWindsBalm, null, SkillB.DazzlingStaff.lv(3), null)
        createItem(Name.リン__総選挙_, 0, WeaponType.BOW, MoveType.CAVALRY, 5, 16, 7, 9, 5, 6, 5, 8, 8, 2, 6,
                Bow.Mulagir, null, Special.DraconicAura, SkillA.SwiftSparrow.lv(2), SkillB.SacaesBlessing, SkillC.AtkSmoke.lv(3))
        createItem(Name.ルセア, 0, WeaponType.STAFF, MoveType.INFANTRY, 4, 18, 6, 8, 3, 9, 5, 8, 6, 1, 8,
                Staff.Pain, Assist.Martyr, Special.Miracle, SkillA.Hp.lv(3), null, null)
        createItem(Name.レオ, 0, WeaponType.BOW, MoveType.INFANTRY, 4, 17, 8, 6, 8, 5, 6, 8, 7, 6, 1,
                Bow.SlayingBow2, null, Special.Ignis, SkillA.Speed.lv(3), SkillB.Guard.lv(3), null)
        createItem(Name.レベッカ, 0, WeaponType.BOW, MoveType.INFANTRY, 4, 18, 7, 8, 6, 5, 5, 6, 8, 3, 6,
                Bow.SilverBow2, Assist.ArdentSacrifice, null, SkillA.DartingBlow.lv(3), SkillB.SealAtk.lv(3), null)

        createItem(Name.ヘンリー__収穫祭_, 3, WeaponType.GTOME, MoveType.ARMORED, 5, 17, 9, 10, 4, 12, 5, 7, 7, 6, 7,
                Gtome.SpectralTome2, null, Special.Reprisal, null, SkillB.LiveForHonor, SkillC.ArmorMarch.lv(3))
        createItem(Name.ジョーカー__収穫祭_, 0, WeaponType.BOW, MoveType.ARMORED, 5, 20, 9, 6, 9, 8, 6, 8, 4, 7, 7,
                Bow.MonstrousBow2, null, null, SkillA.BracingBlow.lv(2), SkillB.WaryFighter.lv(3), null)
        createItem(Name.ノノ__収穫祭_, 1, WeaponType.RTOME, MoveType.FLIER, 5, 17, 8, 6, 5, 8, 4, 8, 8, 3, 5,
                Rtome.Grimoire, Assist.Reposition, null, SkillA.AtkResBond.lv(3), SkillB.LiveForBounty, SkillC.HoneAtk.lv(3))
        createItem(Name.サクラ__収穫祭_, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 16, 8, 8, 4, 8, 4, 7, 8, 1, 8,
                Dagger.KittyPaddle2, null, Special.Glacies, SkillA.WardingStance.lv(3), SkillB.Guard.lv(3), SkillC.DaggerValor.lv(3))

        createItem(Name.ドルカス, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 19, 9, 6, 9, 5, 8, 8, 4, 8, 5,
                Axe.StoutTomahawk, null, Special.DraconicAura, SkillA.FierceStance.lv(3), SkillB.QuickRiposte.lv(3), SkillC.InfantryPulse.lv(3))
        createItem(Name.ルーテ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 10, 8, 3, 8, 4, 8, 7, 2, 8,
                Btome.WeirdingTome, Assist.RallyAtkRes, null, SkillA.HpRes.lv(2), null, SkillC.ResPloy.lv(3))
        createItem(Name.ワユ, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 16, 8, 12, 6, 6, 6, 7, 9, 6, 5,
                Sword.ResoluteBlade, null, Special.Luna, SkillA.FlashingBlade.lv(3), SkillB.Vantage.lv(3), null)

        createItem(Name.ヨシュア, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 18, 7, 9, 8, 6, 6, 7, 8, 6, 6,
                Sword.Audhulma, null, Special.Moonbow, SkillA.CloseDef.lv(3), SkillB.Windsweep.lv(3), null)
        createItem(Name.フィヨルム, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 17, 8, 7, 8, 8, 6, 6, 7, 6, 8,
                Lance.Leiptr, null, Special.IceMirror, SkillA.AtkResBond, SkillB.ShieldPulse.lv(3), SkillC.DriveAtk.lv(2))
        createItem(Name.シャラ, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 17, 9, 8, 4, 7, 5, 8, 8, 4, 4,
                Gtome.KeenGronnwolf2, Assist.RallyAtkDef, null, SkillA.DistantDef, null, SkillC.SavageBlow.lv(3))
        createItem(Name.シノノメ, 2, WeaponType.LANCE, MoveType.INFANTRY, 5, 19, 9, 6, 9, 5, 6, 8, 7, 8, 4,
                Lance.BrightNaginata, Assist.Swap, null, SkillA.SteadyStance.lv(3), null, SkillC.DefTactic.lv(3))
        createItem(Name.ジークベルト, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 19, 8, 9, 7, 3, 6, 8, 8, 7, 2,
                Sword.DarkGreatsword, null, Special.DragonFang, SkillA.DeathBlow.lv(3), null, SkillC.AtkTactic.lv(3))
        createItem(Name.ソレイユ, 1, WeaponType.SWORD, MoveType.INFANTRY, 4, 18, 10, 9, 6, 5, 5, 9, 8, 6, 5,
                Sword.FiresweepSword2, null, Special.BlazingWind, SkillA.DartingBlow.lv(3), null, SkillC.DriveRes.lv(2))

        createItem(Name.クロム__冬_, 3, WeaponType.AXE, MoveType.ARMORED, 5, 25, 12, 4, 9, 5, 8, 9, 3, 8, 7,
                Axe.SackOGifts2, Assist.Pivot, null, SkillA.BrazenAtkDef.lv(3), SkillB.WaryFighter.lv(3), null)
        createItem(Name.リズ__冬_, 3, WeaponType.AXE, MoveType.ARMORED, 5, 21, 9, 8, 9, 8, 6, 7, 6, 8, 8,
                Axe.Handbell2, null, Special.Bonfire, null, SkillB.BoldFighter, SkillC.FortifyArmor)
        createItem(Name.ルフレ__冬_, 2, WeaponType.LANCE, MoveType.ARMORED, 5, 23, 9, 8, 9, 6, 6, 8, 8, 8, 5,
                Lance.Tannenboom2, Assist.ReciprocalAid, Special.BlazingWind, SkillA.BrazenAtkSpd.lv(3), null, SkillC.ArmorMarch.lv(3))
        createItem(Name.サーリャ__冬_, 1, WeaponType.RTOME, MoveType.ARMORED, 5, 19, 10, 5, 8, 10, 7, 7, 2, 8, 8,
                Rtome.Candelabra2, null, Special.Iceberg, SkillA.CloseCounter, SkillB.VengefulFighter.lv(3), SkillC.RTomeValor.lv(3))

        createItem(Name.スリーズ, 3, WeaponType.GTOME, MoveType.CAVALRY, 5, 15, 7, 9, 6, 6, 6, 7, 7, 2, 5,
                Gtome.Blizzard, null, Special.Glacies, SkillA.FortressRes.lv(3), SkillB.ChillingSeal, SkillC.ResPloy.lv(3))
        createItem(Name.アクア__正月_, 3, WeaponType.AXE, MoveType.FLIER, 5, 15, 6, 9, 5, 4, 6, 7, 8, 4, 6,
                Axe.Hagoita2, Assist.Sing, null, null, SkillB.EarthDance.lv(3), SkillC.HoneFliers)
        createItem(Name.カミラ__正月_, 1, WeaponType.SWORD, MoveType.FLIER, 5, 18, 9, 9, 7, 4, 6, 7, 8, 7, 5,
                Sword.Kadomatsu2, null, Special.DraconicAura, SkillA.SpdDefBond.lv(3), null, SkillC.WardFliers)
        createItem(Name.タクミ__正月_, 0, WeaponType.DAGGER, MoveType.INFANTRY, 4, 18, 8, 10, 4, 5, 6, 8, 7, 2, 6,
                Dagger.KagamiMochi2, null, Special.Moonbow, SkillA.AtkResBond.lv(3), SkillB.BowBreaker.lv(3), null)
        createItem(Name.カムイ__正月_, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 18, 8, 8, 6, 5, 5, 7, 6, 9, 2,
                Bow.HamaYa2, Assist.RallyDefRes, null, SkillA.AtkDef.lv(2), null, SkillC.SpurDefRes.lv(2))

        createItem(Name.エイリーク__追憶_, 1, WeaponType.RTOME, MoveType.CAVALRY, 5, 17, 7, 9, 5, 5, 5, 7, 8, 3, 4,
                Rtome.Gleipnir, Assist.RallyAtkSpd, null, SkillA.SwiftSparrow.lv(2), SkillB.Desperation.lv(3), SkillC.RTomeValor.lv(2))
        createItem(Name.ラーチェル, 2, WeaponType.BTOME, MoveType.CAVALRY, 5, 16, 9, 7, 4, 7, 4, 7, 7, 2, 7,
                Btome.Ivaldi, null, Special.GrowingLight, null, SkillB.Renewal.lv(3), SkillC.ResTactic.lv(3))
        createItem(Name.ミルラ, 3, WeaponType.DRAGON, MoveType.FLIER, 5, 16, 7, 6, 3, 7, 8, 8, 5, 11, 7,
                Breath.GreatFlame, null, Special.Bonfire, SkillA.Fury.lv(3), null, SkillC.HoneDragons)
        createItem(Name.リオン, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 19, 10, 4, 4, 8, 6, 7, 4, 6, 6,
                Rtome.Naglfar, null, Special.Retribution, SkillA.AtkRes.lv(2), null, SkillC.DriveRes.lv(2))

        createItem(Name.アイク__伝承英雄_, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 19, 10, 6, 9, 4, 6, 8, 7, 8, 4,
                Sword.Ragnell, null, Special.RadiantAether, SkillA.WardingBreath, SkillB.SealAtkDef.lv(2), SkillC.DefTactic.lv(3))

        createItem(Name.ヘクトル__バレンタイン_, 3, WeaponType.AXE, MoveType.ARMORED, 5, 24, 8, 4, 12, 7, 8, 10, 4, 9, 4,
                Axe.BerserkArmads, null, Special.Glimmer, SkillA.DistantCounter, SkillB.WaryFighter.lv(3), null)
        createItem(Name.リリーナ__バレンタイン_, 3, WeaponType.GTOME, MoveType.CAVALRY, 5, 17, 9, 8, 5, 4, 4, 8, 6, 3, 6,
                Gtome.GreenGift2, null, Special.BlazingFlame, SkillA.HpAtk.lv(2), null, SkillC.AtkTactic.lv(3))
        createItem(Name.リン__バレンタイン_, 2, WeaponType.BTOME, MoveType.ARMORED, 5, 18, 9, 10, 5, 10, 5, 6, 8, 6, 7,
                Btome.BlueGift2, Assist.ArdentSacrifice, null, SkillA.AtkSpdBond.lv(3), SkillB.Guard.lv(3), SkillC.ArmorMarch.lv(3))
        createItem(Name.ロイ__バレンタイン_, 0, WeaponType.BOW, MoveType.CAVALRY, 5, 17, 9, 6, 5, 6, 4, 8, 2, 6, 7,
                Bow.Gratia2, Assist.ReciprocalAid, null, SkillA.DeathBlow.lv(3), null, SkillC.BowValor.lv(3))
        createItem(Name.エリウッド__バレンタイン_, 2, WeaponType.LANCE, MoveType.ARMORED, 4, 23, 10, 8, 7, 7, 7, 9, 7, 6, 6,
                Lance.CasaBlanca2, Assist.RallyAtkDef, null, SkillA.FireBoost.lv(3), null, SkillC.GoadArmor)

        createItem(Name.ゼルギウス, 1, WeaponType.SWORD, MoveType.ARMORED, 5, 22, 10, 7, 10, 6, 7, 8, 8, 9, 3,
                Sword.Alondite, null, Special.BlackLuna, SkillA.FierceStance.lv(3), SkillB.WarpPowder, SkillC.PanicPloy.lv(3))
        createItem(Name.ミカヤ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 9, 6, 5, 9, 5, 8, 6, 2, 8,
                Btome.Thani, Assist.Sacrifice, null, SkillA.DistantDef.lv(3), SkillB.Guard.lv(3), SkillC.DriveAtk.lv(2))
        createItem(Name.サザ, 0, WeaponType.DAGGER, MoveType.INFANTRY, 4, 17, 10, 9, 5, 4, 6, 8, 7, 4, 4,
                Dagger.Peshkatz, null, Special.Glimmer, SkillA.LifeAndDeath.lv(3), null, SkillC.SpurAtkSpd.lv(2))

        createItem(Name.セリカ__闇_, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 9, 9, 5, 7, 6, 8, 8, 6, 5,
                Sword.BelovedZofia, null, Special.Luna, null, SkillB.ChillSpd.lv(3), SkillC.HoneAtk.lv(3))
        createItem(Name.ハーディン__闇_, 2, WeaponType.LANCE, MoveType.ARMORED, 5, 23, 8, 6, 10, 8, 6, 8, 6, 8, 7,
                Lance.Gradivus, null, Special.Vengeance, SkillA.BrazenDefRes.lv(3), SkillB.BoldFighter.lv(3), null)
        createItem(Name.ルフレ__闇_, 3, WeaponType.DRAGON, MoveType.ARMORED, 5, 24, 10, 7, 9, 5, 7, 10, 4, 8, 6,
                Breath.Expiration, null, Special.Ignis, null, SkillB.VengefulFighter.lv(3), SkillC.WardDragons)

        createItem(Name.タクミ__闇_, 0, WeaponType.BOW, MoveType.INFANTRY, 3, 15, 9, 10, 6, 5, 6, 6, 7, 5, 5,
                Bow.Skadi, null, Special.Vengeance, SkillA.Fury.lv(3), null, SkillC.SpdSmoke.lv(3))
        createItem(Name.エフラム__伝承英雄_, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 19, 10, 5, 9, 3, 7, 8, 6, 7, 3,
                Lance.FlameSiegmund, null, Special.Sol, SkillA.SturdyStance.lv(2), SkillB.SolarBrace, SkillC.FortifyDef.lv(3))

        createItem(Name.クロム__聖痕_, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 19, 9, 6, 8, 4, 6, 9, 6, 8, 2,
                Sword.SealedFalchion, null, Special.Aether, SkillA.Fury.lv(3), SkillB.ChillDef.lv(3), SkillC.SwordValor.lv(3))
        createItem(Name.マーク__男_, 1, WeaponType.RTOME, MoveType.INFANTRY, 4, 18, 7, 8, 5, 7, 5, 8, 8, 3, 5,
                Rtome.GrimasTruth, null, Special.DragonFang, null, SkillB.DullRanged.lv(3), SkillC.SpurDefRes.lv(2))
        createItem(Name.マーク__女_, 2, WeaponType.BTOME, MoveType.FLIER, 5, 18, 8, 6, 3, 9, 5, 8, 7, 2, 7,
                Btome.Blarserpent2, null, Special.Iceberg, SkillA.MirrorStance.lv(2), SkillB.Guard.lv(3), SkillC.AtkPloy.lv(3))


        createItem(Name.カチュア__春_, 2, WeaponType.BTOME, MoveType.CAVALRY, 5, 17, 6, 9, 5, 6, 5, 8, 8, 2, 4,
                Btome.HuginnsEgg, Assist.DrawBack, null, null, SkillB.ChillRes.lv(3), SkillC.DriveSpd.lv(3))
        createItem(Name.アルフォンス__春_, 3, WeaponType.AXE, MoveType.CAVALRY, 5, 19, 9, 7, 6, 5, 6, 8, 8, 7, 2,
                Axe.GiantSpoon2, null, Special.Noontime, SkillA.SturdyBlow.lv(2), null, SkillC.DefSmoke.lv(3))

        createItem(Name.シャロン__春_, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 18, 8, 10, 3, 6, 5, 7, 8, 2, 7,
                Gtome.MuninnsEgg, Assist.RallyAtkSpd, null, SkillA.SwiftStance.lv(2), SkillB.LiveForBounty, SkillC.ResTactic.lv(3))

        createItem(Name.カゲロウ__春_, 0, WeaponType.DAGGER, MoveType.FLIER, 5, 17, 9, 8, 5, 5, 5, 8, 8, 2, 6,
                Dagger.LethalCarrot2, null, Special.Glimmer, SkillA.SpdResBond.lv(3), SkillB.LiveForHonor, SkillC.GoadFliers)


        createItem(Name.ジェローム, 3, WeaponType.AXE, MoveType.FLIER, 4, 19, 10, 6, 8, 4, 8, 9, 5, 8, 3,
                Axe.Poleaxe2, Assist.RallyAtkDef, null, SkillA.FortressDef.lv(3), SkillB.HitAndRun, null)
        createItem(Name.ルフレ__女闇_, 0, WeaponType.DRAGON, MoveType.FLIER, 5, 16, 8, 9, 8, 6, 7, 7, 8, 6, 5,
                Breath.Expiration, null, Special.Bonfire, SkillA.Dragonskin, SkillB.CancelAffinity.lv(3), SkillC.ResSmoke.lv(3))

        createItem(Name.リーフ, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 6, 10, 8, 6, 6, 8, 9, 6, 4,
                Sword.LightBrand, null, Special.BlazingLight, SkillA.SteadyBlow.lv(2), SkillB.SDrink, SkillC.DriveAtk.lv(2))
        createItem(Name.ナンナ, 0, WeaponType.STAFF, MoveType.CAVALRY, 4, 17, 5, 8, 6, 7, 5, 6, 8, 3, 5,
                Staff.Absorb2, Assist.Restore, Special.HeavenlyLight, SkillA.SpdRes.lv(2), null, SkillC.DriveDef.lv(2))
        createItem(Name.ラインハルト__トラキア_, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 18, 9, 6, 7, 6, 6, 8, 4, 7, 6,
                Sword.Meisterschwert, null, Special.Pavise, SkillA.DeathBlow.lv(3), SkillB.Vantage.lv(3), SkillC.SpurAtkSpd.lv(2))
        createItem(Name.オルエン__トラキア_, 3, WeaponType.GTOME, MoveType.CAVALRY, 5, 17, 8, 9, 4, 5, 4, 6, 8, 3, 6,
                Gtome.Thunderhead, null, Special.BlazingWind, SkillA.SwiftSparrow.lv(2), SkillB.Renewal.lv(3), SkillC.GTomeValor.lv(3))
        createItem(Name.フィン, 2, WeaponType.LANCE, MoveType.CAVALRY, 4, 18, 8, 9, 8, 3, 6, 8, 7, 7, 3,
                Lance.BraveLance2, null, Special.Miracle, SkillA.AtkDef.lv(2), null, SkillC.GoadCavalry)
        createItem(Name.サイアス, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 17, 7, 8, 5, 8, 5, 8, 6, 2, 8,
                Btome.WargodsTome, null, Special.Glacies, null, SkillB.EscapeRoute.lv(3), SkillC.SpdPloy.lv(3))

        createItem(Name.ヒノカ__白き翼_, 0, WeaponType.BOW, MoveType.FLIER, 5, 18, 6, 9, 4, 7, 5, 8, 8, 2, 6,
                Bow.WarriorPrincess, null, Special.Luna, SkillA.AtkSpdBond.lv(3), SkillB.FlierFormation.lv(3), SkillC.FlierGuidance.lv(3))
        createItem(Name.シグレ__白き翼_, 2, WeaponType.LANCE, MoveType.FLIER, 4, 18, 7, 9, 5, 8, 6, 8, 9, 6, 4,
                Lance.HarmonicLance2, null, Special.Noontime, SkillA.DartingStance.lv(3), null, SkillC.WardFliers)
        createItem(Name.カンナ__女_, 3, WeaponType.DRAGON, MoveType.INFANTRY, 5, 18, 8, 8, 7, 7, 6, 6, 8, 8, 5,
                Breath.WaterBreath2, null, Special.DraconicAura, SkillA.FierceStance.lv(3), null, SkillC.GoadDragons)
        createItem(Name.リン__伝承英雄_, 3, WeaponType.BOW, MoveType.INFANTRY, 5, 17, 9, 10, 4, 5, 5, 6, 8, 4, 6,
                Bow.SwiftMulagir, Assist.RallyDefRes, null, SkillA.LawsOfSacae, SkillB.Desperation.lv(3), SkillC.SpdTactic.lv(3))
        createItem(Name.スズカゼ, 0, WeaponType.DAGGER, MoveType.INFANTRY, 3, 17, 6, 10, 4, 8, 4, 7, 8, 2, 8,
                Dagger.BarbShuriken2, null, Special.Iceberg, null, SkillB.PoisonStrike.lv(3), SkillC.AtkSmoke.lv(3))
        createItem(Name.カンナ__男_, 2, WeaponType.DRAGON, MoveType.INFANTRY, 3, 18, 9, 7, 7, 7, 6, 6, 8, 7, 6,
                Breath.WaterBreath2, null, Special.DragonFang, SkillA.BrazenDefRes.lv(3), null, SkillC.FortifyDragons)
        createItem(Name.アレス, 1, WeaponType.SWORD, MoveType.CAVALRY, 4, 18, 8, 8, 7, 5, 6, 9, 6, 8, 2,
                Sword.DarkMystletainn, null, Special.DraconicAura, SkillA.BrazenAtkDef.lv(3), SkillB.SealDefRes.lv(3), null)
        createItem(Name.リーン, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 16, 6, 7, 4, 6, 5, 6, 9, 5, 6,
                Sword.Safeguard2, Assist.Dance, null, null, SkillB.FirestormDance.lv(2), SkillC.SwordValor.lv(3))
        createItem(Name.イシュタル, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 8, 10, 4, 7, 6, 8, 8, 2, 5,
                Btome.Mjolnir, null, Special.Moonbow, SkillA.SwiftSparrow.lv(2), SkillB.Vantage.lv(3), SkillC.OddAtkWave.lv(3))
        createItem(Name.ユリウス, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 16, 9, 8, 3, 9, 6, 8, 5, 2, 8,
                Rtome.Loptous, null, Special.DraconicAura, null, SkillB.Guard.lv(3), SkillC.AtkPloy.lv(3))

        createItem(Name.サーリャ__花嫁_, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 17, 9, 9, 6, 4, 6, 8, 8, 3, 4,
                Rtome.MuspellFireposy, Assist.RallyAtkSpd, null, SkillA.AtkSpdBond.lv(3), SkillB.SpdFeint.lv(3), null)
        createItem(Name.ニニアン__花嫁_, 2, WeaponType.BTOME, MoveType.FLIER, 5, 15, 6, 7, 3, 5, 5, 6, 8, 3, 6,
                Btome.FreshBouquet2, Assist.Dance, null, null, SkillB.ChillAtk.lv(3), SkillC.DriveSpd.lv(2))
        createItem(Name.サナキ__花嫁_, 3, WeaponType.GTOME, MoveType.FLIER, 5, 16, 10, 8, 3, 7, 5, 8, 6, 2, 8,
                Gtome.NiflFrostflowers, Assist.DrawBack, null, SkillA.AtkResBond.lv(3), SkillB.DefResLink.lv(3), null)
        createItem(Name.マルス__花婿_, 3, WeaponType.AXE, MoveType.CAVALRY, 4, 18, 7, 10, 6, 5, 6, 7, 8, 7, 3,
                Axe.ArdentService2, Assist.RallySpdDef, null, null, SkillB.WingsOfMercy.lv(3), SkillC.DriveAtk.lv(2))

        createItem(Name.リョウマ__伝承英雄_, 1, WeaponType.SWORD, MoveType.FLIER, 5, 17, 8, 11, 6, 5, 7, 8, 9, 6, 3,
                Sword.Raijinto, null, Special.Glimmer, SkillA.KestrelStance.lv(2), SkillB.Bushido, SkillC.Guidance.lv(3))

        createItem(Name.カアラ, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 7, 10, 6, 7, 7, 9, 10, 4, 3,
                Sword.VassalsBlade, null, Special.DraconicAura, null, SkillB.Wrath.lv(3), SkillC.EvenSpdWave.lv(3))
        createItem(Name.ニノ__失われし牙_, 3, WeaponType.GTOME, MoveType.FLIER, 5, 17, 7, 10, 4, 6, 5, 8, 9, 2, 5,
                Gtome.GigaExcalibur, null, Special.Moonbow, SkillA.SwiftSparrow.lv(2), SkillB.Aerobatics.lv(3), SkillC.SpdSmoke.lv(3))
        createItem(Name.ラガルト, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 17, 7, 10, 7, 4, 6, 7, 9, 4, 3,
                Dagger.TheCleaner2, null, Special.Glimmer, SkillA.SwiftStrike.lv(2), null, SkillC.AtkTactic.lv(3))

        createItem(Name.ライナス, 3, WeaponType.AXE, MoveType.INFANTRY, 3, 18, 9, 9, 8, 4, 7, 9, 7, 7, 3,
                Axe.Basilikos, null, Special.Luna, SkillA.BrazenAtkSpd.lv(3), null, SkillC.DriveDef.lv(2))
        createItem(Name.ターナ__夏_, 1, WeaponType.RTOME, MoveType.FLIER, 5, 16, 7, 10, 5, 6, 5, 8, 8, 3, 5,
                Rtome.FruitOfIdunn, null, null, SkillA.AtkSpdPush.lv(3), SkillB.Renewal.lv(3), SkillC.SpurAtkDef.lv(2))
        createItem(Name.ティアモ__夏_, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 17, 8, 10, 6, 5, 7, 7, 9, 6, 2,
                Lance.ShellLance2, Assist.HarshCommand, null, SkillA.SturdyBlow.lv(2), SkillB.DullRanged.lv(3), null)
        createItem(Name.ヒーニアス__夏_, 3, WeaponType.AXE, MoveType.FLIER, 5, 18, 9, 9, 6, 5, 7, 7, 9, 7, 3,
                Axe.BeachBanner2, Assist.RallyAtkDef, null, null, SkillB.DefFeint.lv(3), SkillC.GoadFliers)
        createItem(Name.ノワール__夏_, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 17, 8, 10, 5, 5, 5, 7, 8, 6, 3,
                Bow.Cocobow2, Assist.ArdentSacrifice, null, SkillA.AtkSpd.lv(2), null, SkillC.InfantryRush.lv(3))
        createItem(Name.ヘクトル__伝承英雄_, 3, WeaponType.AXE, MoveType.ARMORED, 5, 23, 10, 6, 12, 4, 7, 10, 4, 8, 6,
                Axe.ThunderArmoads, null, Special.DraconicAura, SkillA.DistantCounter, SkillB.VengefulFighter, SkillC.OstiasPulse)

        createItem(Name.チキ__幼夏_, 1, WeaponType.DRAGON, MoveType.FLIER, 5, 16, 7, 4, 8, 4, 7, 8, 11, 7, 6,
                Breath.SummersBreath, null, Special.Moonbow, SkillA.AtkDefBond.lv(3), SkillB.HitAndRun, SkillC.DragonValor.lv(3))
        createItem(Name.カミラ__夏_, 2, WeaponType.BTOME, MoveType.FLIER, 5, 17, 7, 10, 6, 7, 6, 8, 8, 4, 3,
                Btome.JuicyWave2, null, Special.DraconicAura, SkillA.DeathBlow.lv(3), SkillB.FlierFormation.lv(3), SkillC.HoneFliers)
        createItem(Name.リンダ__夏_, 0, WeaponType.DAGGER, MoveType.CAVALRY, 5, 16, 8, 9, 3, 7, 4, 8, 8, 2, 5,
                Dagger.Starfish2, Assist.ArdentSacrifice, null, SkillA.BrazenAtkSpd.lv(3), SkillB.CancelAffinity.lv(3), null)
        createItem(Name.タクミ__夏_, 0, WeaponType.BOW, MoveType.FLIER, 5, 18, 9, 7, 6, 4, 6, 8, 9, 3, 3,
                Bow.FishieBow2, null, Special.Glimmer, SkillA.Fury.lv(3), null, SkillC.OddResWave.lv(3))
        createItem(Name.スミア, 2, WeaponType.LANCE, MoveType.FLIER, 5, 17, 6, 11, 7, 6, 6, 7, 9, 6, 5,
                Lance.ReprisalLance2, Assist.Reposition, null, SkillA.CloseDef.lv(3), SkillB.AtkDefLink.lv(3), null)
        createItem(Name.マリアベル, 0, WeaponType.STAFF, MoveType.CAVALRY, 5, 17, 9, 9, 3, 5, 4, 8, 7, 2, 6,
                Staff.Trilemma2, Assist.Martyr, Special.Miracle, null, SkillB.DazzlingStaff.lv(3), SkillC.StaffValor.lv(3))
        createItem(Name.オリヴィエ__聖王国_, 1, WeaponType.SWORD, MoveType.FLIER, 5, 17, 6, 8, 4, 4, 5, 7, 8, 6, 5,
                Sword.Skuld, Assist.Dance, null, SkillA.BracingStance.lv(2), SkillB.ChillSpd.lv(3), SkillC.Guidance.lv(3))
        createItem(Name.リベラ, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 18, 9, 8, 6, 7, 6, 8, 7, 6, 6,
                Axe.WoGun2, null, Special.Noontime, null, SkillB.Renewal.lv(3), SkillC.SpurAtkRes.lv(2))


        createItem(Name.ルキナ__伝承英雄_, 2, WeaponType.BOW, MoveType.INFANTRY, 5, 17, 8, 9, 6, 5, 5, 8, 8, 5, 3,
                Bow.Thogn, Assist.FutureVision, null, SkillA.SwiftSparrow.lv(3), SkillB.WingsOfMercy, SkillC.DistantGuard.lv(3))

        createItem(Name.エリンシア__夏祭り_, 3, WeaponType.DAGGER, MoveType.INFANTRY, 5, 15, 5, 7, 5, 4, 5, 7, 9, 3, 4,
                Dagger.CloudMaiougi2, Assist.Dance, null, SkillA.AtkSpdPush.lv(3), SkillB.RockslideDance.lv(3), SkillC.DriveRes.lv(2))
        createItem(Name.リョウマ__夏祭り_, 2, WeaponType.DAGGER, MoveType.INFANTRY, 5, 14, 6, 8, 4, 4, 6, 8, 8, 4, 2,
                Dagger.SkyMaiougi2, Assist.Dance, null, SkillA.TriangleAdept.lv(3), SkillB.ChillDef.lv(3), SkillC.SpurSpdDef)
        createItem(Name.マークス__夏祭り_, 0, WeaponType.DAGGER, MoveType.INFANTRY, 5, 16, 7, 4, 6, 3, 6, 9, 2, 9, 2,
                Dagger.DuskUchiwa2, Assist.Dance, null, SkillA.CloseCounter, SkillB.QuickRiposte.lv(3), SkillC.OddAtkWave)
        createItem(Name.ミカヤ__夏祭り_, 1, WeaponType.RTOME, MoveType.INFANTRY, 5, 14, 8, 5, 3, 6, 5, 8, 3, 3, 9,
                Rtome.DawnSuzu, Assist.Dance, null, SkillA.AtkResBond.lv(3), SkillB.FirefloodDance.lv(2), SkillC.ResPloy.lv(3))

        createItem(Name.エフラム__総選挙_, 3, WeaponType.AXE, MoveType.ARMORED, 5, 22, 10, 5, 11, 7, 7, 9, 6, 8, 5,
                Axe.Garm, null, Special.DraconicAura, SkillA.CloseDef.lv(3), SkillB.SpecialFighter.lv(3), SkillC.ArmorMarch.lv(3))
        createItem(Name.ヘクトル__総選挙_, 2, WeaponType.LANCE, MoveType.ARMORED, 5, 24, 11, 5, 9, 6, 7, 8, 4, 10, 6,
                Lance.Maltet, null, Special.Ignis, SkillA.OstianCounter, SkillB.BoldFighter.lv(3), SkillC.EvenResWave.lv(3))
        createItem(Name.セリカ__総選挙_, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 16, 7, 11, 7, 7, 6, 8, 9, 6, 4,
                Sword.RoyalSword, null, Special.Galeforce, SkillA.DeathBlow.lv(4), SkillB.DoubleLion, SkillC.AtkTactic.lv(3))
        createItem(Name.ヴェロニカ__総選挙_, 0, WeaponType.STAFF, MoveType.CAVALRY, 5, 17, 8, 10, 4, 4, 5, 7, 8, 4, 3,
                Staff.Hliðskjálf, Assist.Recover2, Special.WindfireBalm2, null, SkillB.WrathfulStaff.lv(3), SkillC.CloseGuard.lv(3))

        createItem(Name.マルス__伝承英雄_, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 18, 8, 10, 8, 4, 6, 8, 8, 7, 4,
                Sword.ExaltedFalchion, null, Special.FireEmblem, SkillA.AtkSpdBond.lv(3), SkillB.BindingShield, SkillC.InfantryFlash.lv(3))
        createItem(Name.ジャムカ, 1, WeaponType.BOW, MoveType.INFANTRY, 3, 16, 8, 10, 7, 4, g50, g65, g50, g50, g30,
                Bow.SlayingBow2, null, Special.Astra, SkillA.HeavyBlade.lv(3), SkillB.CancelAffinity.lv(3), null)
        createItem(Name.シルヴィア, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 16, 5, 8, 4, 6, g55, g50, g65, g30, g55,
                Sword.BarrierBlade2, Assist.Dance, null, SkillA.MirrorStance.lv(2), SkillB.DelugeDance.lv(2), null)
        createItem(Name.キュアン, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 20, 11, 5, 7, 3, g55, g60, g55, g55, g30,
                Lance.GaeBolg, Assist.RallySpdDef2, Special.DraconicAura, SkillA.SteadyPosture.lv(2), null, SkillC.DriveAtk.lv(2))
        createItem(Name.レヴィン, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 16, 8, 11, 4, 6, g50, g60, g60, g30, g45,
                Gtome.Forseti, null, Special.Glimmer, SkillA.SwiftSparrow.lv(2), SkillB.SpecialSpiral.lv(3), SkillC.OddAtkWave.lv(3))

        createItem(Name.フローラ, 1, WeaponType.DAGGER, MoveType.INFANTRY, 5, 16, 8, 6, 5, 10, g50, g60, g40, g30, g65,
                Dagger.HoarfrostKnife, null, Special.Iceberg, SkillA.AtkResSolo.lv(3), SkillB.QuickRiposte.lv(3), SkillC.DefPloy.lv(3))
        createItem(Name.オフェリア, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 16, 7, 9, 5, 8, g50, g70, g55, g35, g35,
                Btome.Missiletainn, Assist.RallyUpAtk2, Special.BlazingLight, SkillA.SturdyBlow.lv(2), SkillB.ChillRes.lv(3), null)
        createItem(Name.エポニーヌ, 0, WeaponType.BOW, MoveType.INFANTRY, 5, 16, 8, 11, 3, 7, g45, g55, g60, g30, g55,
                Bow.ShiningBow2, Assist.DrawBack, Special.Moonbow, null, SkillB.DefResLink.lv(3), SkillC.BowValor.lv(3))
        createItem(Name.サイラス, 2, WeaponType.LANCE, MoveType.CAVALRY, 5, 18, 9, 5, 10, 4, g50, g60, g45, g60, g40,
                Lance.SlayingLance2, Assist.Reposition, null, SkillA.SteadyStance.lv(3), null, SkillC.EvenDefWave.lv(3))
        createItem(Name.ガロン, 1, WeaponType.DRAGON, MoveType.INFANTRY, 3, 17, 9, 5, 9, 8, g60, g55, g40, g55, g55,
                Breath.BreathOfBlight, null, Special.DragonFang, SkillA.DistantDef.lv(3), null, SkillC.PanicPloy.lv(3))
        createItem(Name.レーギャルン, 1, WeaponType.SWORD, MoveType.FLIER, 5, 17, 8, 10, 8, 4, g55, g55, g60, g65, g30,
                Sword.Niu, null, Special.Bonfire, SkillA.RDuelFlying.lv(3), SkillB.ChillAtk.lv(3), SkillC.DistantGuard.lv(3))
        createItem(Name.レーヴァテイン, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 17, 11, 7, 8, 5, g50, g60, g55, g60, g40,
                Sword.Laevatein, Assist.Swap, null, SkillA.Fury.lv(3), SkillB.AtkDefLink.lv(3), SkillC.OddAtkWave.lv(3))
        createItem(Name.ヘルビンディ, 3, WeaponType.AXE, MoveType.INFANTRY, 5, 18, 9, 5, 9, 7, g60, g60, g30, g60, g55,
                Axe.Byleistr, null, Special.Vengeance, SkillA.GDuelInfantry.lv(3), SkillB.Guard.lv(3), SkillC.InfantryPulse.lv(3))
        createItem(Name.チキ__伝承英雄_, 2, WeaponType.DRAGON, MoveType.ARMORED, 5, 17, 10, 11, 4, 5, g65, g55, g55, g75, g55,
                Breath.DivineMist, null, Special.Moonbow, SkillA.FierceStance, SkillB.BoldFighter.lv(3), SkillC.WithEveryone)

        createItem(Name.ミルラ__収穫祭_, 1, WeaponType.DRAGON, MoveType.ARMORED, 5, 20, 8, 4, 7, 8, g60, g70, g45, g75, g55,
                Breath.SpiritBreath, null, Special.Bonfire, SkillA.DefRes.lv(2), SkillB.VengefulFighter.lv(3), SkillC.ArmorMarch.lv(3))
        createItem(Name.ゼロ__収穫祭_, 2, WeaponType.BOW, MoveType.ARMORED, 5, 18, 10, 11, 6, 7, g45, g55, g65, g45, g50,
                Bow.DevilishBow2, Assist.Smite, null, SkillA.SwiftSparrow.lv(2), SkillB.AtkDefLink.lv(3), SkillC.FortifyArmor)
        createItem(Name.カゲロウ__収穫祭_, 3, WeaponType.DAGGER, MoveType.ARMORED, 5, 19, 12, 4, 8, 9, g50, g60, g40, g55, g55,
                Dagger.BottledJuice2, null, Special.DraconicAura, SkillA.MirrorStrike.lv(2), SkillB.BoldFighter.lv(3), SkillC.EvenResWave)
        createItem(Name.ワユ__収穫祭_, 0, WeaponType.STAFF, MoveType.FLIER, 5, 17, 6, 10, 5, 6, g45, g55, g55, g40, g50,
                Staff.WitchyWand2, Assist.Recover2, Special.HeavenlyLight, SkillA.SpdResBond.lv(3), null, SkillC.HoneFliers.lv(3))
        createItem(Name.ロキ, 0, WeaponType.STAFF, MoveType.INFANTRY, 5, 18, 9, 9, 3, 6, g55, g55, g60, g30, g45,
                Staff.Thokk, Assist.Restore2, Special.SolidEarthBalm, SkillA.CDuelInfantry.lv(3), null, SkillC.OddAtkWave.lv(3))

        createItem(Name.クリフ, 2, WeaponType.BTOME, MoveType.INFANTRY, 5, 15, 6, 4, 3, 9, g50, g55, g75, g50, g45,
                Btome.Sagittae, null, Special.GrowingLight, SkillA.FortressDefRes.lv(3), SkillB.ChillSpd.lv(3), SkillC.FortifyRes.lv(3))
        createItem(Name.ウード, 1, WeaponType.SWORD, MoveType.INFANTRY, 5, 17, 8, 9, 7, 7, g55, g55, g65, g55, g35,
                Sword.Missiletainn, null, Special.BlueFlame, SkillA.AtkDefBond.lv(3), SkillB.Wrath.lv(3), SkillC.SpurSpdDef.lv(2))

        createItem(Name.インバース, 1, WeaponType.RTOME, MoveType.FLIER, 5, 18, 7, 8, 4, 7, g55, g60, g60, g30, g40,
                Rtome.AversasNight, null, Special.DragonFang, SkillA.HpSpd.lv(2), null, SkillC.OddResWave.lv(3))
        createItem(Name.エイリーク__伝承英雄_, 1, WeaponType.SWORD, MoveType.CAVALRY, 5, 16, 5, 11, 8, 6, g50, g60, g65, g45, g35,
                Sword.StormSieglinde, null, Special.Moonbow, SkillA.AtkSpdSolo.lv(3), SkillB.LunarBrace, SkillC.OddDefWave.lv(3))

        createItem(Name.カムイ__男夢_, 2, WeaponType.DRAGON, MoveType.INFANTRY, 5, 16, 7, 7, 5, 5, g60, g65, g65, g60, g45,
                Breath.DraconicRage, null, Special.DragonFang, SkillA.AtkDefBond.lv(3), SkillB.NullFollowUp.lv(3), SkillC.HoneDragons)
        createItem(Name.カムイ__女夢_, 3, WeaponType.DRAGON, MoveType.INFANTRY, 5, 15, 7, 7, 6, 5, g60, g55, g70, g60, g50,
                Breath.DraconicRage, Assist.RallyAtkSpd2, Special.DraconicAura, SkillA.AtkSpdBond.lv(3), null, SkillC.FortifyDragons)
        createItem(Name.カミラ__夢_, 1, WeaponType.RTOME, MoveType.FLIER, 5, 17, 9, 8, 4, 6, g45, g65, g50, g30, g55,
                Rtome.BookOfDreams, Assist.DrawBack, null, SkillA.AtkResBond.lv(3), SkillB.AtkDefLink.lv(3), null)
        createItem(Name.ミコト__夢_, 0, WeaponType.STAFF, MoveType.INFANTRY, 5, 16, 9, 8, 5, 7, g50, g60, g55, g35, g45,
                Staff.Flash2, Assist.Martyr2, Special.Miracle, SkillA.BrazenAtkRes.lv(3), null, SkillC.InfantryRush.lv(3))
        createItem(Name.アクア__夢_, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 14, 7, 5, 4, 6, g50, g60, g55, g30, g45,
                Gtome.BookOfShadows, Assist.Sing, null, SkillA.SpdResBond.lv(3), SkillB.TorrentDance.lv(3), null)
        createItem(Name.ガーネフ, 1, WeaponType.RTOME, MoveType.INFANTRY, 3, 16, 8, 10, 5, 6, g50, g50, g55, g50, g40,
                Rtome.Imhullu, null, Special.Ignis, SkillA.MirrorStance.lv(3), SkillB.ChillAtk.lv(3), null)
//あれ太ったおっさん作り忘れたか？
        createItem(Name.ユルグ, 2, WeaponType.DAGGER, MoveType.INFANTRY, 5, 16, 7, 10, 7, 5, g50, g60, g65, g35, g35,
                Dagger.Sylgr, null, Special.Glimmer, SkillA.SorceryBlade.lv(3), SkillB.ChillSpd.lv(3), SkillC.SpdTactic.lv(3))
        createItem(Name.スルト, 3, WeaponType.AXE, MoveType.ARMORED, 5, 17, 9, 4, 10, 7, g75, g70, g30, g70, g60,
                Axe.Sinmara, null, Special.Bonfire, SkillA.SteadyStance.lv(4), SkillB.WaryFighter.lv(3), null)
                createItem(Name.フリーズ, 1, WeaponType.SWORD, MoveType.INFANTRY,  5, 19, 7, 4, 12, 4, g50, g70, g30, g55, g50,
                        Sword.Gjoll, null, Special.Moonbow, SkillA.DistantCounter, SkillB.FreezingSeal, SkillC.AtkSmoke.lv(3))

        createItem(Name.エイル, 0, WeaponType.DAGGER, MoveType.FLIER,  5, 16, 8, 9, 4, 7, g45, g55, g65, g25, g55,
                Dagger.Lyfjaberg, null, Special.Iceberg, SkillA.SwiftSparrow.lv(2), SkillB.MysticBoost.lv(3), SkillC.SparklingBoost)
        //        createItem(Name., 1, WeaponType., MoveType.,  5, , , , , , , , , , ,
        //                Weapon., Assist., Special., SkillA..lv(), SkillB..lv(), SkillC..lv())
//        createItem(Name.スルト, 3, WeaponType.AXE, MoveType.ARMORED, 5, 20, 11, 4, 12, 9, g75, g65, g25, g65, g60,
//                Weapon.Sinmara, null, null, null, null, null)
//        createItem(Name.ザカリア, 2, WeaponType.BTOME, MoveType.CAVALRY, 5, 16, 7, 9, 5, 6, g40, g50, g60, g40, g45,
//                Weapon.Valaskjalf, null, null, null, null, null)
//        createItem(Name.ヴェロニカ, 3, WeaponType.GTOME, MoveType.INFANTRY, 5, 16, 7, 9, 5, 6, g40, g50, g60, g40, g45,
//                Weapon.Elivagar, null, null, null, null, null)
    }

    private const val g25 = 1
    private const val g30 = 2
    private const val g35 = 3
    private const val g40 = 4
    private const val g45 = 5
    private const val g50 = 6
    private const val g55 = 7
    private const val g60 = 8
    private const val g65 = 9
    private const val g70 = 10
    private const val g75 = 11
    //    private val g80 = 12
    private fun createItem(
            name: Name = Name.NONE,
            color: Int = 0,
            weaponType: WeaponType = WeaponType.SWORD,
            moveType: MoveType = MoveType.INFANTRY,
            minRarity: Int = 0,
            hp: Int = 0,
            atk: Int = 0,
            spd: Int = 0,
            def: Int = 0,
            res: Int = 0,
            hpGrowth: Int = 0,
            atkGrowth: Int = 0,
            spdGrowth: Int = 0,
            defGrowth: Int = 0,
            resGrowth: Int = 0,
            weapon: Weapon? = null,
            assist: Assist? = null,
            special: Special? = null,
            //本当はSkill分けたいのだがNONEやLappedSkillをうまく扱えない。いっそSkillsに戻すか？
            aSkill: Skill? = null,
            bSkill: Skill? = null,
            cSkill: Skill? = null,
            refinedWeapon: RefinedWeapon? = null
    ) {
        val item = BaseHero(color, weaponType, moveType, minRarity, name, hp, atk, spd, def, res, hpGrowth, atkGrowth, spdGrowth, defGrowth, resGrowth, weapon
                ?: Skill.NONE, assist ?: Skill.NONE, special ?: Skill.NONE, aSkill
                ?: Skill.NONE, bSkill ?: Skill.NONE, cSkill ?: Skill.NONE, refinedWeapon
                ?: Skill.NONE)
        ITEMS.add(item)
        ITEM_MAP[item.name.jp] = item
        ITEM_MAP[item.name.tw] = item
        ITEM_MAP[item.name.us] = item
    }

}