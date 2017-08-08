
function findFundListByEmpId() {
    var empId = document.getElementById("srchOwnerId").value;
    PortfolioDwr.findFundListByEmpId(empId, fillFundList);
}
function fillFundList(xiangArray) {
    var defaultOption = realPortfolioSearchForm.fundId.options[0];
    var defaultValue = defaultOption.value;
    var defaultText = defaultOption.text;
    DWRUtil.removeAllOptions("fundId");
    DWRUtil.addOptions("fundId", [{value:defaultValue,text:defaultText}], "value", "text");
    var array = new Array();
    for (var i = 0; i < xiangArray.length; i++) {
        array[i] = {value:xiangArray[i].value,    text:xiangArray[i].label};
    }
    DWRUtil.addOptions("fundId", array, "value", "text");

    findCombiListByEmpFundId();
}

function findCombiListByEmpFundId() {
    var empId = document.getElementById("srchOwnerId").value;
    var fundId = document.getElementById("fundId").value;
    PortfolioDwr.findCombiListByEmpFundId(empId, fundId, fillCombiList);
}

function fillCombiList(xiangArray) {
    var defaultOption = realPortfolioSearchForm.fundId.options[0];
    var defaultValue = defaultOption.value;
    var defaultText = defaultOption.text;
    DWRUtil.removeAllOptions("combiId");
    DWRUtil.addOptions("combiId", [{value:defaultValue,text:defaultText}], "value", "text");
    var array = new Array();
    for (var i = 0; i < xiangArray.length; i++) {
        array[i] = {value:xiangArray[i].value,    text:xiangArray[i].label};
    }
    DWRUtil.addOptions("combiId", array, "value", "text");
}

function findRealPflListByEmpId_() {
    var empId = document.getElementById("srchOwnerId").value;
    var srchStatus = document.getElementById("srchStatus").value;
    PortfolioDwr.findRealPflListByEmpId(empId,srchStatus,fillRealPflList);
}

function fillRealPflList(xiangArray) {
    var defaultOption = realPortfolioSearchForm.realPortfoliId.options[0];
    var defaultValue = defaultOption.value;
    var defaultText = defaultOption.text;
    DWRUtil.removeAllOptions("realPortfoliId");
    DWRUtil.addOptions("realPortfoliId", [{value:defaultValue,text:defaultText}], "value", "text");
    var array = new Array();
    for (var i = 0; i < xiangArray.length; i++) {
        array[i] = {value:xiangArray[i].value,    text:xiangArray[i].label};
    }
    DWRUtil.addOptions("realPortfoliId", array, "value", "text");
}

function updateColor(){
    var table = document.getElementById('table');
    var length = table.rows.length;
    if (length > 1) {
        for (var i = 2; i < length; i++)
        {
            var pre = document.getElementById('table').rows[i - 1].cells[0].innerText;
            var curr = document.getElementById('table').rows[i].cells[0].innerText;
            if (pre == curr) {
                document.getElementById('table').rows[i].bgColor = document.getElementById('table').rows[i - 1].bgColor;
            } else {
                if (document.getElementById('table').rows[i - 1].bgColor == "#eeeeee") {
                    document.getElementById('table').rows[i].bgColor = "#ffffff";
                } else {
                    document.getElementById('table').rows[i].bgColor = "#eeeeee";
                }
            }
        }
    }
}
