function print_today() {
    var now = new Date();
    var months = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
    var date = ((now.getDate() < 10) ? "0" : "") + now.getDate();

    function fourdigits(number) {
        return (number < 1000) ? number + 1900 : number;
    }
    var today = months[now.getMonth()] + " " + date + ", " + (fourdigits(now.getYear()));
    return today;
}

function roundNumber(number, decimals) {
    var newString;
    decimals = Number(decimals);
    if (decimals < 1) {
        newString = (Math.round(number)).toString();
    } else {
        var numString = number.toString();
        if (numString.lastIndexOf(".") == -1) {
            numString += ".";
        }
        var cutoff = numString.lastIndexOf(".") + decimals;
        var d1 = Number(numString.substring(cutoff, cutoff + 1));
        var d2 = Number(numString.substring(cutoff + 1, cutoff + 2));
        if (d2 >= 5) {
            if (d1 == 9 && cutoff > 0) {
                while (cutoff > 0 && (d1 == 9 || isNaN(d1))) {
                    if (d1 != ".") {
                        cutoff -= 1;
                        d1 = Number(numString.substring(cutoff, cutoff + 1));
                    } else {
                        cutoff -= 1;
                    }
                }
            }
            d1 += 1;
        }
        if (d1 == 10) {
            numString = numString.substring(0, numString.lastIndexOf("."));
            var roundedNum = Number(numString) + 1;
            newString = roundedNum.toString() + '.';
        } else {
            newString = numString.substring(0, cutoff) + d1.toString();
        }
    }
    if (newString.lastIndexOf(".") == -1) {
        newString += ".";
    }
    var decs = (newString.substring(newString.lastIndexOf(".") + 1)).length;
    for (var i = 0; i < decimals - decs; i++) newString += "0";
    return newString;
}

function update_total() {
    var total = 0;
    $('.price').each(function(i) {
        price = $(this).html().replace("$", "");
        if (!isNaN(price)) total += Number(price);
    });
    total = roundNumber(total, 2);
    $('#subtotal').html("$" + total);
    $('#total').html("$" + total);
    update_balance();
}

function update_balance() {
    var due = $("#total").html().replace("$", "") - $("#paid").val().replace("$", "");
    due = roundNumber(due, 2);
    $('.due').html("$" + due);
}

function update_price() {
    var row = $(this).parents('.item-row');
    var price = row.find('.cost').val().replace("$", "") * row.find('.qty').val();
    price = roundNumber(price, 2);
    isNaN(price) ? row.find('.price').html("N/A") : row.find('.price').html("$" + price);
    update_total();
}

function bind() {
    $(".cost").blur(update_price);
    $(".qty").blur(update_price);
}
function removeInvoiceRow(ele) {
    $(ele).parents('.item-row').remove();
}
$(document).ready(function() {
    $('input').on('click', function () {
        $(this).select();
    });
    $("#paid").blur(update_balance);
    $("#addrow").on('click', function () {
        $(".item-row:last").after('<tr class="item-row"><td class="item-name"><div class="delete-wpr"><textarea>Item Name</textarea><a class="delete" href="javascript:;" title="Remove row" onClick="removeInvoiceRow(this)">X</a></div></td><td class="description"><textarea>Description</textarea></td><td><textarea class="cost">$0</textarea></td><td><textarea class="qty">0</textarea></td><td><span class="price">$0</span></td></tr>');
        if ($(".delete").length > 0) $(".delete").show();
        bind();
    });
    bind();
    $(".delete").on('click', function() {
        removeInvoiceRow(this);
        update_total();
        if ($(".delete").length < 2) $(".delete").hide();
    });
    $("#cancel-logo").on('click', function () {
        $("#logo").removeClass('edit');
    });
    $("#delete-logo").on('click', function () {
        $("#logo").remove();
    });
    $("#change-logo").on('click', function () {
        $("#logo").addClass('edit');
        $("#imageloc").val($("#image").attr('src'));
        $("#image").select();
    });
    $("#save-logo").on('click', function () {
        $("#image").attr('src', $("#imageloc").val());
        $("#logo").removeClass('edit');
    });
    $("#date").val(print_today());

    
});