// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

@main
struct shopApp: App {
    init() {
        setupApperance()
    }
    
    var body: some Scene {
        WindowGroup {
            TabbarView().accentColor(.main_color)
        }
    }
    
    private func setupApperance() {
        UINavigationBar.appearance().largeTitleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor(named: "main_color")]
        
        UINavigationBar.appearance().titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor(named: "main_color")]
        
        UIBarButtonItem.appearance().setTitleTextAttributes([NSAttributedString.Key.foregroundColor: UIColor(named: "main_color"),], for: .normal)
        
        UIWindow.appearance().tintColor = UIColor(named: "main_color")
    }
}

struct TabbarView: View {
    @State var selectedTab = Tab.rankLists
    
    enum Tab: Int {
        case rankLists, search, subscription, setting
    }
    
    func tabbarItem(text: String, image: String) -> some View {
        VStack {
            Image(systemName: image)
                .imageScale(.large)
            Text(text)
        }
    }
    
    var body: some View {
        TabView(selection: $selectedTab) {
            HomeView().tabItem{
                self.tabbarItem(text: "首页", image: "house")
            }.tag(Tab.rankLists)
            CategoryView().tabItem{
                self.tabbarItem(text: "分类", image: "list.bullet")
            }.tag(Tab.search)
            CartView().tabItem{
                self.tabbarItem(text: "购物车", image: "cart")
            }.tag(Tab.subscription)
            MineView().tabItem{
                self.tabbarItem(text: "我的", image: "person")
            }.tag(Tab.setting)
        }
    }
}
