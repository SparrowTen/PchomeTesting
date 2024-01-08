Feature: Login
  Background: 初始化 Login 測試案列
    Given 初始化 Login 測試案列

  Scenario: 登入成功
    Given 使用者導航至登入頁面
    When 使用者在電子郵件欄位中輸入有效的電子郵件地址 "EMAIL"
    And 使用者點擊繼續按鈕
    When 使用者在密碼欄位中輸入有效的密碼 "PASSWORD"
    And 使用者點擊登入按鈕
    Then 使用者應該登入成功

  Scenario: 登入失敗(密碼錯誤)
    Given 使用者導航至登入頁面
    When 使用者在電子郵件欄位中輸入有效的電子郵件地址 "EMAIL"
    And 使用者點擊繼續按鈕
    When 使用者在密碼欄位中輸入有效的密碼 " "
    And 使用者點擊登入按鈕
    Then 使用者應該登入失敗