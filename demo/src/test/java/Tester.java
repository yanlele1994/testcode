import com.bjpowernode.pojo.Account;
import com.bjpowernode.service.AccountService;
import com.bjpowernode.service.impl.AccountServiceImpl;
import com.bjpowernode.service.impl.AccountServiceProxy;
import com.bjpowernode.utils.ProxyUtils;
import org.junit.Test;

public class Tester {

    @Test
    public void test01() {
        //AccountService accountService = new AccountServiceImpl();
        //AccountService accountServiceProxy = new AccountServiceProxy(accountService);

        // 代理类，和被代理类实现了相同的接口，因此这里可以使用多态
        AccountService accountServiceProxy = ProxyUtils.getProxy(AccountServiceImpl.class);
        accountServiceProxy.transfer("tom", "jack", 100);
        Account account = accountServiceProxy.get("tom");
        System.out.println(account);
    }
}
