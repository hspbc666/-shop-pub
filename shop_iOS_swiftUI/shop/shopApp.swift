//
//  shopApp.swift
//  shop
//
//  Created by jerry on 2022/2/4.
//

import SwiftUI

@main
struct shopApp: App {
    init() {
        setupApperance()
    }
    
    var body: some Scene {
        WindowGroup {
            TabbarView().accentColor(.tsmg_blue)
        }
    }
    
    private func setupApperance() {
        UINavigationBar.appearance().largeTitleTextAttributes = [
            NSAttributedString.Key.foregroundColor: UIColor(named: "tsmg_blue")]
        
        UINavigationBar.appearance().titleTextAttributes = [
            NSAttributedString.Key.foregroundColor: UIColor(named: "tsmg_blue")]
        
        UIBarButtonItem.appearance().setTitleTextAttributes([
            NSAttributedString.Key.foregroundColor: UIColor(named: "tsmg_blue"),
        ], for: .normal)
        
        UIWindow.appearance().tintColor = UIColor(named: "tsmg_blue")
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
