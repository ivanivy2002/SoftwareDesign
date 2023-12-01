import org.example.client.Client;
import org.example.utils.FileAccessor;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
//import org.mockito.Mockito;

public class FunctionalTest {
    @Test
    public void testListWorkspace() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test1.md");
        FileAccessor.clearFile("./data/test2.md");
        client.execute("load test1.md");
        client.execute("append-head # 新的标题1");
        client.execute("save");
        client.execute("load test2.md");
        client.execute("append-head # 新的标题2");
        client.execute("list-workspace");
        client.execute("save");
        client.execute("list-workspace");
        assert client.getWorkspaceManager().getWorkspaceMap().size() == 2;
    }

    @Test
    public void testActive() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test1.md");
        FileAccessor.clearFile("./data/test2.md");
        client.execute("load test1.md");
        client.execute("append-head # 新的标题1");
        client.execute("save");
        assert client.getWorkspaceManager().getWorkspaceMap().get("test1").isActive();
        client.execute("load test2.md");
        client.execute("append-head # 新的标题2");
        client.execute("list-workspace");
        assert client.getWorkspaceManager().getWorkspaceMap().get("test2").isActive();
        assert !client.getWorkspaceManager().getWorkspaceMap().get("test1").isActive();
    }

    @Test
    public void testOpen() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test1.md");
        FileAccessor.clearFile("./data/test2.md");
        client.execute("load test1.md");
        client.execute("append-head # 新的标题1");
        client.execute("save");
        client.execute("load test2.md");
        client.execute("append-head # 新的标题2");
        client.execute("list-workspace");
        client.execute("open test1");
        client.execute("list-workspace");
        assert client.getWorkspaceManager().getCur().getName().equals("test1");
    }

    @Test
    public void testCloseYes() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test1.md");
        FileAccessor.clearFile("./data/test2.md");
        client.execute("load test1.md");
        client.execute("append-head # 新的标题1@test1");
        client.execute("save");
        client.execute("load test2.md");
        client.execute("append-head # 新的标题2@test2");
        client.execute("list-workspace");
//         模拟用户输入
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(inputStream);
        client.execute("close");
        // 模拟用户输入
        client.execute("list-workspace");
        assert client.getWorkspaceManager().getCur() == null;
        client.execute("load test2.md");
        client.execute("list-workspace");
        client.execute("list");
    }

    @Test
    public void testCloseNo() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test1.md");
        FileAccessor.clearFile("./data/test2.md");
        client.execute("load test1.md");
        client.execute("append-head # 新的标题1");
        client.execute("save");
        client.execute("load test2.md");
        client.execute("append-head # 新的标题2");
        client.execute("list-workspace");
        // 模拟用户输入
        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n".getBytes());
        System.setIn(inputStream);
        client.execute("close");
        client.execute("list-workspace");
        assert client.getWorkspaceManager().getCur() == null;
        client.execute("load test2.md");
        client.execute("list-workspace");
        client.execute("list");
    }

    @Test
    public void testExit() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test1.md");
        FileAccessor.clearFile("./data/test2.md");
        FileAccessor.clearFile("./data/test3.md");
        client.execute("load test1.md");
        client.execute("append-head # 新的标题1@TEST1");
        client.execute("save");
        client.execute("load test2.md");
        client.execute("append-head # 新的标题2@TEST2");
        client.execute("load test3.md");
        client.execute("append-head # 新的标题3@TEST3");
        client.execute("list-workspace");
        client.execute("open test2");
        client.execute("list-workspace");
        client.execute("list");
        client.execute("exit-slowly");
        client.execute("list-workspace");
    }


}
