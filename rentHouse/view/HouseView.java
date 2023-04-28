package rentHouse.view;


import rentHouse.domain.House;
import rentHouse.service.HouseService;
import rentHouse.utils.Utility;


public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    private HouseService houseService = new HouseService(10); //没懂

    //列出房屋信息
    public void listHouse() {
        System.out.println("-------------房屋列表-------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();//等到house对象列表信息

        //houses[0] =  new House(1,"lkf","123","上海",1000,0);

        for (int i = 0; i < houses.length; i++) {
            System.out.println(houses[i]);
        }
        System.out.println("-------------房屋列表显示完毕-------------");
    }

    //添加房屋
    public void addHouse() {
        System.out.println("-------------添加房屋-------------");
        System.out.print("姓名:");
        String name = Utility.readString(8);
        System.out.print("电话:");
        String phone = Utility.readString(12);
        System.out.print("地址:");
        String address = Utility.readString(16);
        System.out.print("月租:");
        int rent = Utility.readInt();
        System.out.print("状态:");
        int state = Utility.readInt(3);

        House house = new House(0, name, phone, address, rent,state);

        if (houseService.add(house)) {
            System.out.println("-------------添加房屋成功-------------");
        }else {
            System.out.println("-------------添加房屋失败-------------");
        }
    }


    public void delHouse(){
        System.out.println("-------------删除房屋-------------");
        System.out.println("请输入待删除房屋的编号(-1退出):");
        int delId = Utility.readInt();
        if(delId == -1){
            System.out.println("-------------放弃删除房屋信息-------------");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if(choice =='Y'){
            if (houseService.del(delId)){
                System.out.println("-------------删除房屋信息成功-------------");
            }else {
                System.out.println("-------------房屋信息不存在，删除失败-------------");
            }
        }else {
            System.out.println("-------------放弃删除房屋信息-------------");
        }
    }

    public void findHouse() {
        System.out.println("=============查找房屋信息============");
        System.out.print("请输入要查找的id: ");
        int findId = Utility.readInt();
        //调用方法
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("=============查找房屋信息id不存在============");
        }
    }

    public void exit() {
        //这里我们使用Utility提供方法，完成确认
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    public void update() {
        System.out.println("=============修改房屋信息============");
        System.out.println("请选择待修改房屋编号(-1表示退出)");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("=============你放弃修改房屋信息============");
            return;
        }

        //根据输入得到updateId，查找对象

        //老师特别提示:返回的是引用类型[即:就是数组的元素]
        //因此老师在后面对house.setXxx() ,就会修改HouseService中houses数组的元素!!!!!!!!!!
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("=============修改房屋信息编号不存在..============");
            return;
        }

        System.out.print("姓名(" + house.getName() + "): ");
        String name = Utility.readString(8, "");//这里如果用户直接回车表示不修改该信息,默认""
        if (!"".equals(name)) {//修改
            house.setName(name);
        }

        System.out.print("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.print("地址(" + house.getAddress() + "): ");
        String address = Utility.readString(18, "");
        if (!"".equals(address)) {
            house.setAddress(address);
        }
        System.out.print("租金(" + house.getRent() + "):");
        int rent = Utility.readInt(-1);
        if (rent != -1) {
            house.setRent(rent);
        }
        System.out.print("状态(" + house.getState() + "):");
        int state = Utility.readInt();
        if (!"".equals(state)) {
            house.setState(state);
        }
        System.out.println("=============修改房屋信息成功============");


    }


    public void mainMenu() {
        do {
            System.out.println("-------------房屋出租系统-------------");
            System.out.println("\t\t\t1 新增房源");
            System.out.println("\t\t\t2 查找房屋");
            System.out.println("\t\t\t3 删除房屋");
            System.out.println("\t\t\t4 修改房屋信息");
            System.out.println("\t\t\t5 房屋列表");
            System.out.println("\t\t\t6 退出");
            System.out.print("请输入需要的选项:");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }

}
