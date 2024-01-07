Feature: Search
  Scenario: 搜尋商品
    Given 使用者在搜尋欄位中輸入 "APPLE"
    When 使用者點擊搜尋按鈕
    Then 使用者應該看到搜尋結果

  Scenario: 回到頁首
    When 使用者在頁面底部
    Given 使用者點擊回到頁首按鈕
    Then 使用者應該看到頁面最上方

  Scenario: 限時瘋搶切換時間
    When 使用者查看限時瘋搶
    Then 使用者查看計時器倒數
    Given 使用者點擊0800
    Given 使用者點擊1200