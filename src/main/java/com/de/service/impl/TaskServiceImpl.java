package com.de.service.impl;

import com.de.dao.TaskDao;
import com.de.entity.Task;
import com.de.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:实现类
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

//上传成功返回个数，失败返回0
    @Override
    public int upTask(Task task) {
            int result = taskDao.upTask(task);
            return result;
    }

    @Override
    public int deleteTaskById(Integer id) {
            int result = taskDao.deleteTaskById(id);
            return result;
    }

    @Override
    public int deleteTaskByIds(List<Integer> tIds) {
        int result = taskDao.deleteTaskByIds(tIds);
        return result;
    }

    @Override
    public List<Task> selectEmpTaskByEmpId(Integer empId) {
            List<Task> tasks = taskDao.selectEmpTaskByEmpId(empId);
            return tasks;
    }

    @Override
    public int updateTaskById(Integer id, Task task) {
            int result = taskDao.updateTaskById(id, task);
            return result;
    }
}
