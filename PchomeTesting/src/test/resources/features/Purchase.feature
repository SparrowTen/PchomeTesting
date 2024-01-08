Feature: Purchase
  Background: 初始化 Purchase 測試案列
    Given 初始化 Purchase 測試案列
    
  Scenario: 購買商品
    Given 使用者查看購物車
    And 使用者使用LINE PAY付款
    Then 使用者應該按下確認送出
