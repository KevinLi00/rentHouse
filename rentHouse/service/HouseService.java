package rentHouse.service;

import rentHouse.domain.House;

/*业务层 执行增删改查*/
public class HouseService {

    private House house[];
    private int housenums = 0;
    private int idcounter = 1;

    public HouseService(int size) {
        house = new House[size];
    }

    public House[] list() {
        return house;
    }

    public boolean add(House newhouse) {
        if (housenums == house.length) {
            System.out.println("数组已满，不能再添加");
            return false;
        }
        house[housenums++] = newhouse;
        newhouse.setId(idcounter++);
        return true;
    }

    public boolean del(int delId) {
        int index = -1;
        for (int i = 0; i < housenums; i++) {
            if (delId == house[i].getId()) {
                index = i;
            }
        }

        if (index == -1) {
            return false;
        }

        for (int i = index; i < housenums - 1; i++) {
            house[i] = house[i + 1];
        }

        house[--housenums] = null;
        return true;
    }

    public House findById(int findId) {

        //遍历数组
        for(int i = 0; i < housenums; i++) {
            if(findId == house[i].getId()) {
                return house[i];
            }
        }

        return null;

    }

}
