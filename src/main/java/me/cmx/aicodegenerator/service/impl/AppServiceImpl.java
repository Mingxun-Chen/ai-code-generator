package me.cmx.aicodegenerator.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import me.cmx.aicodegenerator.model.entity.App;
import me.cmx.aicodegenerator.mapper.AppMapper;
import me.cmx.aicodegenerator.service.AppService;
import org.springframework.stereotype.Service;

/**
 * 应用 服务层实现。
 *
 * @author smuca
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>  implements AppService{

}
