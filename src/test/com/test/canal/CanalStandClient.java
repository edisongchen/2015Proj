package com.test.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * ref https://github.com/alibaba/canal/wiki/ClientExample
 * 默认的，如果updat时，column都没有改变，则不会被检测到。
 * print column时都有update=true/false 标识变化前后是否更改
 */
public class CanalStandClient {

    /**
     * standalone canal server,client
     */
    @Test
    public void SimpleClientTest() {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("slave-haixue2",
                11111), "example", "", "");
        doConnect(connector);
    }

    /**
     * zk server ha架构 使用memory+zk保证 canal server的高可用
     * 使用spring/default-instance.xml 先写内存，再写zk
     * canal server相关信息存储到zk上，当有一个canal server failover，另一个热备将启用
     */
    @Test
    public void zkClusterTest() {
        CanalConnector connector = CanalConnectors.newClusterConnector("master-haixue:2181", "example", "", "");
        doConnect(connector);
    }

    private void doConnect(CanalConnector connector) {
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(1000); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (size == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    printEntry(message.getEntries());
                }

                // connector.rollback(batchId); // 处理失败, 回滚数据
                connector.ack(batchId); // 提交确认
            }

        } finally {
            connector.disconnect();
        }
    }

    private void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            String tName = entry.getHeader().getTableName();
            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
//                if (eventType == CanalEntry.EventType.DELETE) {
//                    printColumn(rowData.getBeforeColumnsList());
//                } else
                if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else if (eventType == CanalEntry.EventType.UPDATE) {
                    System.out.println("------->> before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------------------------");
                    printColumn(rowData.getAfterColumnsList());
                    System.out.println("-------<<; after");
                } else {
                    System.out.println("other eventType" + eventType);
                }

            }
        }
    }

    private void processChance(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        if (eventType == CanalEntry.EventType.UPDATE) {

        } else if (eventType == CanalEntry.EventType.INSERT) {

        }
    }

    private void processOgr(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
    }

    private void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.print(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated() + " ");
        }
        System.out.println();
    }
}
