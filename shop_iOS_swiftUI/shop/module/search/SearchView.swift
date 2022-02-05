// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct SearchView: View {
    let langs = [
      "C",
      "C++",
      "Java",
      "Python",
      "JavaScript",
      "Kotlin",
      "Swift",
      "Go",
      "C#",
      "Groovy"
    ]
    @StateObject private var homeViewModel = HomeViewModel()
    @State private var searchText : String = ""
    
    
    var body: some View {
        SearchBar(text: $searchText){
            print(searchText)
        }
        NavigationView{
          List {
            ForEach(self.langs.filter { self.searchText.isEmpty ? true : $0.localizedCaseInsensitiveContains(self.searchText) }, id: \.self) { name in
              Text(name)
            }
          }
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}
