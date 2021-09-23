package com.example.animecomic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animecomic.ui.theme.AnimeComicTheme

/**
 * 主界面
 */
class MainActivity : ComponentActivity() {

    val mListData = listOf(
        MessageData("火影忍者","鸣人",R.drawable.ic_naruto,"我现在决定了，我要走我自己的忍道，朝着一条绝对不会后悔的路，一直往前走"),
        MessageData("星辰变","秦羽",R.drawable.ic_qinyu,"没有人可以帮助自己，只有自己刻苦努力,过去没有人可以，不代表就一定不能。我相信我自己"),
        MessageData("斗破苍穹","萧炎",R.drawable.ic_xiaoyan,"三十年河东，三十年河西，莫欺少年穷,不要太过在意别人的目光，你只要记得，你不是为别人而活着，你为的，是你自己"),
        MessageData("斗罗大陆","唐三",R.drawable.ic_tangsan,"我爱你不是因为你是谁，而是我在你面前可以是谁"),
        MessageData("全职法师","莫凡",R.drawable.ic_mofan,"没有学校，没有高考，你们怎么斗得过富二代"),
        MessageData("百妖谱","桃夭",R.drawable.ic_taoyao,"讲着妖怪的故事，却说着做人的道理"),
        MessageData("武庚纪","逆天而行",R.drawable.ic_ntex,"苦和甜来自外界，坚强则来自内心，来自一个人的自我努力"),
        MessageData("一人之下","冯宝宝",R.drawable.ic_fbaobao,"社会我宝姐，人美路子野,在别人面前尽管装，今后姐罩着你"),
        MessageData("画江湖之不良人","蚩梦",R.drawable.ic_cm,"一天是不良人，一辈都是不良人"),
        MessageData("魔道祖师","莫凡",R.drawable.ic_mdzs,"姑苏有双壁，云梦有双杰"),
        MessageData("关于我转生后成为史莱姆的那件事","萌王利姆鲁",R.drawable.ic_lml,"不管再怎么努力，都有着无法得到的东西。只要从一开始就不知道这些，那么也就不会因此而痛苦了哟")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent 块定义了 activity 的布局
        setContent {
            AnimeComicTheme {
                // A surface container using the 'background' color from the theme
                //脚手架Scaffold的控件帮助开发者快速开发。在Scaffold中提供了很多配件，比如顶部菜单栏、侧滑菜单、底部菜单栏等。
                //并且除了默认的Scaffold外，还有一些类似的控件，比如BackdropScaffold、BottomSheetScaffold等等
                Scaffold(
                    topBar ={
                        TopAppBar(
                            title = {
                                androidx.compose.material.Text(
                                    text = getString(R.string.app_name),
                                )
                            },
                        )
                    }
                ){
//                    Tab()
                    //垂直滚动列表 LazyColumn
                    LazyColumn{
                        items(mListData){message ->
                            list(message)
                        }
                    }
                }
            }
        }
    }

    //@Composable 可组合函数
    @Composable
    fun list(messageData:MessageData){
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Image(painter = painterResource(id = messageData.icon), contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
                )
            Spacer(modifier = Modifier.width(10.dp))//设置间隔，空格多宽
            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false)}
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor:Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )
            // We toggle the isExpanded variable when we click on this Column 当我们单击这个列时，切换isExpanded变量
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = messageData.title,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1//文字排版-副标题1样式
                )
                Spacer(modifier = Modifier.width(6.dp)) //设置间隔宽度
                Text(
                    text = messageData.name,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle2//文字排版-副标题2样式
                )
                Spacer(modifier = Modifier.width(6.dp)) //设置间隔宽度
                //Surface 设计容器
                Surface(
                    //设置形状为 由Card或AlertDialog等媒体组件使用的形状
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,//分割线高度
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = messageData.describe,
                        modifier = Modifier.padding(all = 5.dp),
                        // If the message is expanded, we display all its content 如果消息展开，则显示其所有内容
                        // otherwise we only display the first line 否则我们只显示第一行
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2 //设置排版样式
                        )
                }
            }

        }
    }

    @Composable
    fun Tab(){
        val KEY_ROUTE = "";
        //tab标题
        val listItem = listOf("首页","发现","我的");
        //tab未选图标
        val noSelIcons = listOf(R.drawable.ic_home_nosel,R.drawable.ic_find_nosel,R.drawable.ic_mine_nosel)
        //tab选中图标
        val selIcons = listOf(R.drawable.ic_home_sel,R.drawable.ic_find_sel,R.drawable.ic_mine_sel)
        //记住选中tab位置////remember将值存储起来，当界面发生了重新绘制，就会读之前存储的值
        val selectIndex = remember{
            //标明这个data(数据)是有状态的，如果状态发生了改变，所有引用这个状态的控件都发生了改变，都需要重新绘制！
            mutableStateOf(0)
        }
        //脚手架 (可以理解为类似工地建房子时先回搭一个 空架子，然后人就在这个架子里面建房子
        // 编程上面理解：类似一个小工具 这个工具会提供一些已有的功能方法代码，自己调用使用就行 )
        //脚手架---在Compose的纯函数中需要通过以下方式获取一个有状态的实例
        val navControllers = rememberNavController()
        //Scaffold 脚手架
        Scaffold(
            //Modifier通过链式调用“装饰”我们的Composable，为其添加Background、Padding、onClick事件等
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                //底部导航栏
                BottomNavigation(
                    backgroundColor = Color.White,
                    elevation = 5.dp //底部导航栏头部的分割线高度
                ){
                    //kotlin 中的委托模式依靠by关键字, 将导航返回的栈状态委托给 navBackStackEntry 处理
                    val navBackStackEntry by navControllers.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { androidx.compose.material.Icon(Icons.Filled.Favorite, contentDescription = null) },
                            label = { androidx.compose.material.Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navControllers.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navControllers.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) {innerPadding ->
            NavHost(navControllers, startDestination = Screen.Mine.route, Modifier.padding(innerPadding)) {
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.Find.route) { Find() }
                composable(Screen.Mine.route) { MineActivity() }
            }
        }
    }

    val items = listOf(
        Screen.Home,
        Screen.Find,
        Screen.Mine
    )

    //sealed 密封类 没有构造函数，不可以直接实例化，只能实例化内部的子类
    sealed class Screen(val route: String, @StringRes val resourceId: Int) {
        object Home : Screen("home", R.string.home)
        object Find : Screen("find", R.string.find)
        object Mine : Screen("mine", R.string.mine)
    }

    @Composable
    fun HomeScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.cardview_shadow_end_color))
                .wrapContentSize(Alignment.Center)
        ) {
            androidx.compose.material.Text(
                text = "Home View",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }

    data class MessageData(
        val title:String,
        val name:String,
        val icon:Int,
        val describe:String
    )
}