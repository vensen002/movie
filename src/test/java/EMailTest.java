import org.junit.Test;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author by VENSEN
 * @Classname TestMail
 * @Description TODO()
 * @Date 2020/2/8 14:59
 */
public class EMailTest {
    public static final String SMTPSERVER = "smtp.office365.com";
    public static final String SMTPPORT = "587";
    public static final String ACCOUT = "vensen002@outlook.com";
    public static final String PWD = "SONG643258";

    @Test
    public void testSendMail() throws Exception {
        // 创建邮件配置
        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", SMTPPORT);
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // 根据邮件配置创建会话，注意session别导错包
//        Session session = Session.getDefaultInstance(props);
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vensen002@outlook.com", "SONG643258");//账号密码
            }
        });
        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(true);
        //创建邮件
        MimeMessage message = createEmail(session);
        //获取传输通道
        Transport transport = session.getTransport();
        transport.connect(SMTPSERVER,ACCOUT, PWD);
        //连接，并发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public MimeMessage createEmail(Session session) throws Exception {
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "vensen002", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                "1219877637@qq.com", "test", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(MimeMessage.RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject("测试标题", "utf-8");
        msg.setText("我是个程序员，一天我坐在路边一边喝水一边苦苦检查程序。 这时一个乞丐在我边上坐下了，开始要饭，我觉得可怜，就给了他1块钱。 然后接着调试程序。他可能生意不好，就无聊的看看我在干什么，然后过了一会，他缓缓地指着我的屏幕说，这里少了个分号");
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }

    @Test
    public void test() {
        int i = 123;

        System.out.println( i/=10);
    }
}
