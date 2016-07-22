package contacts.feicui.edu.truesure.treasure;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 宝藏仓库，用来强引用宝藏及区域数据
 */
public class TreasureRepo {

    private static TreasureRepo treasureRepo;

    public static TreasureRepo getInstance(){
        if (treasureRepo == null) {
            treasureRepo = new TreasureRepo();
        }
        return treasureRepo;
    }

    private TreasureRepo() {
    }

    /**
     * 用来保存所有已获取过得区域
     */
    private final HashSet<Area> cachedAreas = new HashSet<>();

    /**
     * 用来保存所有已获取到的宝藏（Map模式还有一个List模式）
     */
    private final HashMap<Integer,Treasure> treasureMap = new HashMap<>();

    public void cache(Area area){
        cachedAreas.add(area);
    }

    public boolean isCached(Area area){
        // 是否已存在(包含)
        // 先比hashCode再比squals
        // 注意重写
        return cachedAreas.contains(area);
    }

    public void addTreasure(List<Treasure> treasureList) {
        for (Treasure treasure : treasureList) {
            treasureMap.put(treasure.getId(), treasure);
        }
    }

    //通过id获取一个宝藏
    public Treasure getTreasure(int id) {
        return treasureMap.get(id);
    }

    //获取所有宝藏
    public Collection<Treasure> getTreasure() {
        return treasureMap.values();
    }

    public void clear() {
        cachedAreas.clear();
        treasureMap.clear();
    }
}
