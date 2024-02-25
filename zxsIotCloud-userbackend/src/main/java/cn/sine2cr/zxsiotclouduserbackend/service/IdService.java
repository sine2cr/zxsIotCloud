package cn.sine2cr.zxsiotclouduserbackend.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 全局唯一Id生成器
 *
 * @author Sine2cr
 * @Date 2024/1/12
 * @Mail sine2cr@163.com
 * @Description 基于雪花算法实现，生成全局唯一id，数据结构改为：1+44+4+4+8+3，44位为时间戳，4位为数据中心id，4位为节点id，8位为序列号，3位为校验码
 **/
@Service
@Scope("singleton")
public class IdService {
    // 自定义的时间起点（2024年1月1日 00:00:00）
    private static final long EPOCH = 1704038400000L;
    //节点位
    private static final long WORKER_ID_BITS = 4L;
    //数据中心位
    private static final long DATACENTER_ID_BITS = 4L;
    //序列号位
    private static final long SEQUENCE_BITS = 8L;
    //校验码位
    private static final long CHECKCODE_BITS = 3L;
    //最大节点数
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    //最大数据中心数
    private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);
    //序列号最大值
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);
    //序列号左移位数
    private static final long SEQUENCE_SHIFT = CHECKCODE_BITS;
    //节点id左移位数
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS + SEQUENCE_SHIFT;
    //数据中心id左移位数
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + CHECKCODE_BITS;
    //时间戳左移位数
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS + SEQUENCE_SHIFT;

    private long checkCode;
    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = 1L;


    private IdService(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("worker Id 不合法");
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException("datacenter Id 不合法");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;

    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        this.checkCode = checkCodeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("系统时间异常");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT) |
                (datacenterId << DATACENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                (sequence << SEQUENCE_SHIFT) |
                checkCode;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    private long checkCodeGen() {
// TODO: 2024/1/13 修改校验码生成方式
        return new Random().nextInt(900);
    }
}
