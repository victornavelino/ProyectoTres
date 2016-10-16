function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

$('.ui-dialog input[type=text].ui-inputtext').val(function () {
    return this.value.toUpperCase();
})

window.alert = function (str) {
    PF('growl').renderMessage({"summary": "Advertencia",
        "detail": str,
        "severity": "warn"})
};

