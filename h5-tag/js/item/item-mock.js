/* Mock Button6 */
function MOCK_load_Button6() {
    var mockData = {
        "contentList" : [
            "111111111111111111", 
            "211111111111111", 
            "344444444", 
            "4444444444444", 
            "5",
            "64444444444",
            "7444444444",
            "4444444444448"
        ],
        "total" : 7,
    };

    load_Button6(mockData, ".button-div-6", "#button-tmp6");
}


/* Mock Icon2 */
function MOCK_load_Icon2() {
    load_Icon2('0', '../img/common/choose/choose1/choose1-chosen.png', '../img/common/choose/choose1/choose1.png');
}

/* Mock Icon4 */
function MOCK_load_Icon4() {
    load_Icon4(".icon-style-4", 5, 0.25);
}

/* Mock Input4 */
function MOCK_load_Input4() {
    load_Input4('.input-style-4');
}

/* Mock Input5 */
function MOCK_load_Input5() {
    let mockData = {
        dValue : "default Valuedefault Valuedefault Valuedefault Valuedefault Value",
        contentList : [
            "11111111111111111111",
            "211111111111111111", 
            "3111111111111111111111", 
            "中文测试", 
            "5ffffffdasfdsafdasfdasfasf",
            "6f22fdsav#@##@#@#@#dbafad",
            "7312323432",
            "811",
            "中文字体测试 "
        ],
        totalCount : 9
    };
    
    load_Input5(mockData, ".input-div-5", "#input-tmp5", true);
}