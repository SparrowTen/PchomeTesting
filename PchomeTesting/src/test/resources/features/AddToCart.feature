Feature: AddToCart
  Background: 初始化 AddToCart 測試案列
    Given 初始化 AddToCart 測試案列

  Scenario: 加入購物車(需選擇規格)
    Given 使用者找到商品 "Apple iPhone 15 (128G)"
    When 使用者點擊連結到商品頁面 "Apple iPhone 15 (128G)"
    And 使用者選擇規格
    And 使用者點擊加入購物車
    Then 使用者應該看到成功加入購物車

  Scenario: 加入購物車(不需選擇規格)
    Given 使用者找到商品 "Apple 第十代 iPad 10.9吋 64G WiFi 銀色"
    When 使用者點擊連結到商品頁面 "Apple 第十代 iPad 10.9吋 64G WiFi 銀色"
    And 使用者點擊加入購物車
    Then 使用者應該看到成功加入購物車