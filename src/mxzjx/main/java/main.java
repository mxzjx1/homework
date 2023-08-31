public class main {
    public static void main(String[] args) {


		 new Client("1");
	     new Client("2");
		 Service service =new Service(); 
	     service.run();
/*        String msg = "你是对的";
        String username = "小孩";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        jsonObject.put("username", username);
        String json = jsonObject.toJSONString();
        System.out.println(json);

        JSONObject jsonObject1 = JSON.parseObject(json);
        String  msg1 = jsonObject1.getString("msg");
        String userneame1 = jsonObject1.getString("username");
        System.out.println(msg);*/



    }

}
