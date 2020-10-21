import com.jcex.open.easysdk.client.OpenClient;
import com.jcex.open.easysdk.model.OpenAuthTokenAppModel;
import com.jcex.open.easysdk.request.CommonRequest;
import com.jcex.open.easysdk.request.OpenAuthTokenAppRequest;
import com.jcex.open.easysdk.request.WayBillRequest;
import com.jcex.open.easysdk.response.CommonResponse;
import com.jcex.open.easysdk.response.OpenAuthTokenAppResponse;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EasySdkTest {


    static final String url = "https://open.jcex.com";

    //String url = "http://127.0.0.1:8081";

    static final String appId= "20201019767777358982676480";

    static final String privateKeyIsv = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCfVCSQknRUV9gg/s6FFppSFKgJlwpWO48oGOR1S+0io6K9VYGwELXi7EJb9YJticuipTtGC8M3LkcRSrBm9k7Gmkq/mdLCXYuDzjh7ZE4lbKSnWcckcKhCKIOUZp/gOXSTq1yAkVwXYG06Wf4Jt3ByeDwTgAdcO3Li2wUV7SNErhsNucVwDiRM/W6OFfHL3BB5L0QY1RbJ6c78+zKL8yA3XigvCUqrSs1J01derN+EYUG2PBtq0yLQn26LhbTn1xX8XRHPPK+BMQe5NyGwEJaiRrOIOVAJXzSmxv34uX2Wh/Srt/cHfVQC7lL1So9Y4j+FOLRQE7yRl/0zVc9pAlIlAgMBAAECggEBAJ3js2x1RcSDXNGt0JiAwqMOh0shm4PC9+WjbVpzyGZoRJBd2OvSU3yx5jzc+ch1IhBmr2uFoonfWVvgaRlPgtc69VIULJCiROx0AvL8fpiFplGVHUyWO5d8MJBydoKsTUM9MKjT46/ixbtqjN2yJEO7tMUaPbD/1KTMqKiG3Elksic494xP8JEvjDN8VUb2ZAT5Azb48uXw2GNu9v7OgWZZWrXt8QOtuGu8ybwnDVPNQrxD651t5e2EzeIz6t9RjhkttWzzpYzDwE+9qmpCyPBsA50Q/7PgNahqxUOKNavpzf9pfA9J4O55LKg24UmAjUpdt+I1usYqqC5qc+KMxO0CgYEA2tS167e2cnHHyndDZDan7dqWTgpb9+vrraXC29FdqgjsI4aIlO3M8p9EK4N7FP5DgnMoQe5zO9ZUmcSVQhCtMqEzcNbs+j4pDmhswfSlv8iwX4YDfzIg7mQAyAk7EpxxpQGEaumsqTC6hRtrNWKXP/LdHmBt2iCgoNfSag+ZcIcCgYEAumQiRi93Nr3cxoxXUlQdM7Gm6xcsCgwJr7CRQJClJCxEO+bAxK5dMiweD0E5jPUhckPL2mUftm7OFF/AbeiKlCiYFFoFNMP5WXpMOFaLxKtoxMuB6sIzyYQpvQYMAqUapMFsrYkxn26OqHcs9qKa9V/mFm5czb5JbnpFxckJ7vMCgYEAmwuPAb4elKOtLPSnWP+op8vyJsUMOHJ9f4EsuNZ0iJ7dwKX3a525UkYp5LK5qDwm1OyGn4A1QTYwpuoz3qU2MeMpMU/2g687I6w1Mo8GSQYGgQfQvnRkBMPyEckIg704f/K9QYri/NU7skelBCQgifynUnh8HMp1RMDloqP+0U0CgYEAhe1BO1ELxceyhO9+JufNe8FggHdL64QuyZCDLfNA1bcA6FGrnSb0TAKrLqbdJU1VVPcl2Kzkln8TkEwkxlRajo6Eh8sPBpTx67sWXTzCfPP0+cKIfRhBgcB3BRz9MEFV3aAl6KM1hqs30hCfvC+mTD015FQ0VpktxjYRQEZt3sUCgYB3Ll7Oj6LOE1B7ioumJbRm4meZXseQ5YrC+3nLjMoIYdIzqrPW0pd7lZyRpeTSR0SoXiIz43N3WnNijdUSfPpo7P4Re7p9ZRgc7W8C7MbfdBHJvwI9JIlqbdKgPaPXkN54IfF3t4BSEGMYl4Qd3iMq15fnq8pRLjKsUUwUXeC8Ow==";

    static final String accessToken = "162c577290c4f4aa63453f99c6f08501";

    OpenClient client = new OpenClient(url, appId, privateKeyIsv,"http://www.jcex.com");

    /*
    获取token
     */
    @Test
    public void testToken() {

        String code = "e255a7728deb83f35ed35c4e437f832d";
        OpenAuthTokenAppRequest request = new OpenAuthTokenAppRequest();
        OpenAuthTokenAppModel model = new OpenAuthTokenAppModel();
        model.setCode(code);
        model.setGrant_type("authorization_code");
        request.setBizModel(model);

        // 根据code获取token
        OpenAuthTokenAppResponse response = client.execute(request);
        if (response.isSuccess()) {
            // 成功拿到token，开发者在这里保存token信息
            // 后续使用token进行接口访问
            System.out.println("返回："+response.getBody());
        }


    }


    /**
     * 获取佳成运单信息
     */
    @Test
    public void testWayBillList() {

        CommonRequest request = new CommonRequest("jcex.tms.waybill.list");
        // 发送请求
        WayBillRequest wayBillRequest = new WayBillRequest();
        wayBillRequest.setPage(1);
        wayBillRequest.setSize(10);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        wayBillRequest.setBeginDate(calendar.getTime());

        wayBillRequest.setEndDate(new Date());
        request.setBizModel(wayBillRequest);

        //注意后面需要accessToken,accessToken 对应到登陆账号...
        CommonResponse response = client.execute(request,accessToken);
        System.out.println("返回："+response.getBody());
    }


    /**
     * 根据单号获取详情
     */
    @Test
    public void testWayBillInfo() {

        CommonRequest request = new CommonRequest("jcex.tms.waybill.info");
        Map<String, Object> bizModel = new HashMap<String, Object>();
        bizModel.put("hawbcode", "JCTES20200921001_D168");
        request.setBizModel(bizModel);
        CommonResponse response = client.execute(request,accessToken);
        System.out.println("返回："+response.getBody());
    }


    /**
     * 获取账户信息
     */
    @Test
    public void testAccountInfo() {

        CommonRequest request = new CommonRequest("jcex.tms.account.info");
        //request.setBizModel(wayBillRequest);
        //注意后面需要accessToken,accessToken 对应到登陆账号...
        CommonResponse response = client.execute(request,accessToken);
        System.out.println("返回："+response.getBody());
    }



}
