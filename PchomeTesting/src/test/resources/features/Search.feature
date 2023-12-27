Feature: Search
  Scenario: 搜尋商品
    Given 使用者在搜尋欄位中輸入 "APPLE"
    When 使用者點擊搜尋按鈕
    Then 使用者應該看到搜尋結果