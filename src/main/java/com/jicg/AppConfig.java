package com.jicg;

import com.jfinal.ext.handler.ContextPathHandler;
import com.jicg.admin.IndexController;
import com.jicg.admin.MeanController;
import com.jicg.admin.PageOneController;
import com.jicg.admin.UploadController;
import com.jicg.admin.auth.AdminAuthInterceptor;
import com.jicg.common.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * 本 jicg 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * <p>
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {

    /**
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     * <p>
     * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
     * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
     * -XX:PermSize=64M -XX:MaxPermSize=256M
     */
    public static void main(String[] args) {
        /**
         * 特别注意：Eclipse 之下建议的启动方式
         */
        //JFinal.start("src/main/webapp", 80, "/", 5);

        /**
         * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
         */
        JFinal.start("src/main/webapp", 8081, "/");
    }

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode", false));
//        me.setJsonFactory(new FastJsonFactory());
        me.setBaseUploadPath("upload/temp");
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add(new Routes() {
            @Override
            public void config() {
                setBaseViewPath("/view/front");
                add("/", com.jicg.front.IndexController.class);

            }
        });
        me.add(new Routes() {
            @Override
            public void config() {
                setBaseViewPath("/view/");
                addInterceptor(new AdminAuthInterceptor());
                add("/admin", IndexController.class);
                add("/admin/sys/mean", MeanController.class);
                add("/admin/website/one", PageOneController.class);
                add("/admin/upload", UploadController.class);
            }
        });

    }

    public void configEngine(Engine me) {
//        me.addSharedFunction("/view/common/_admin_layout.html");
//        me.addSharedFunction("/view/common/_paginate.html");
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
//        me.add(new EhCachePlugin());
        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        me.add(arp);
    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("ctx_path"));
    }


}
