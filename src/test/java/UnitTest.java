//import org.example.workspace.WorkspaceManager;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class UnitTest {
//    WorkspaceManager workspaceManager;
//    @Before
//    public void beforeTestCase() throws IOException {
//        workspaceManager = new WorkspaceManager();
//        workspaceManager.loadFileToActiveWorkspace("test.md");
//        workspaceManager.getActiveWorkspace().executeCommand(new LoadFileCommand(workspaceManager.getActiveWorkspace(), "test.md"));
//    }
//    @After
//    public void afterTestCase() throws IOException {
//        // 清空磁盘存储的 "test.md" 文件内容
//        String filePath = "src/test.md";
//        Path path = Paths.get(filePath);
//        Files.write(path, "".getBytes());
//    }
//    @Test
//    public void testInsert() {
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), 1, "# 旅行清单"));
//        String modifiedContent = workspaceManager.getActiveWorkspace().workspaceContent;
//        assertTrue(modifiedContent.contains("# 旅行清单"));
//    }
//    @Test
//    public void testAppendHead() {
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), 1, "# 旅行清单"));
//        String modifiedContent = workspaceManager.getActiveWorkspace().workspaceContent;
//        assertTrue(modifiedContent.contains("# 旅行清单"));
//    }
//    @Test
//    public void testAppendTail() {
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), -1, "# 旅行清单"));
//        String modifiedContent = workspaceManager.getActiveWorkspace().workspaceContent;
//        assertTrue(modifiedContent.contains("# 旅行清单"));
//    }
//    @Test
//    public void testDelete() {
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), 1, "# 旅行清单"));
//        workspaceManager.getActiveWorkspace().executeCommand(new DeleteCommand(workspaceManager.getActiveWorkspace(), "# 旅行清单"));
//        String modifiedContent = workspaceManager.getActiveWorkspace().workspaceContent;
//        assertTrue(modifiedContent.isEmpty());
//    }
//    @Test
//    public void testUndo() {
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), 1, "# 旅行清单"));
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), -1, "## 亚洲"));
//        workspaceManager.getActiveWorkspace().executeCommand(new UndoCommand(workspaceManager.getActiveWorkspace()));
//        String modifiedContent = workspaceManager.getActiveWorkspace().workspaceContent;
//        assertFalse(modifiedContent.contains("## 亚洲"));
//    }
//    @Test
//    public void testRedo() {
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), 1, "# 旅行清单"));
//        workspaceManager.getActiveWorkspace().executeCommand(new InsertCommand(workspaceManager.getActiveWorkspace(), -1, "## 亚洲"));
//        workspaceManager.getActiveWorkspace().executeCommand(new UndoCommand(workspaceManager.getActiveWorkspace()));
//        workspaceManager.getActiveWorkspace().executeCommand(new RedoCommand(workspaceManager.getActiveWorkspace()));
//        String modifiedContent = workspaceManager.getActiveWorkspace().workspaceContent;
//        assertTrue(modifiedContent.contains("## 亚洲"));
//    }
//
//}
//
