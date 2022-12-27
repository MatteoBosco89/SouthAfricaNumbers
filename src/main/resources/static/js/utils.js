

$(document).ready(function(){

    $('#check').on("click", function(){
        if(!$('#fileform').hasClass('no-display')){
            let file = $('#file');
            if (!file.val()) {
                alert('File not found');
                return;
            }
            $(this).prop("disabled", true);
            $('#spinner').removeClass('no-display');
            uploadFile(file.prop('files')[0]);
        }
        if(!$('#singleNumForm').hasClass('no-display')){
            let num = $('#number');
            if (!num.val()) {
                alert('Invalid Input');
                return;
            }
            $(this).prop("disabled", true);
            $('#spinner').removeClass('no-display');
            checkSingleNum(num.val());
        }
    });
    $('#getCSV').on('click', function(){
        $(this).prop("disabled", true);
        getCSV();
    });
    $('#getList').on('click', function(){
        hideAll()
        $('#numlist').removeClass('no-display');
        $(this).prop("disabled", true);
        listNumbers();
    });
    $('#checkCSV').on('click', function(){
        hideAll()
        $('#fileform').removeClass('no-display');
        $('#check').removeClass('no-display');
    });
    $('#checknum').on('click', function(){
        hideAll()
        $('#singleNumForm').removeClass('no-display');
        $('#check').removeClass('no-display');
    });

});

function hideAll(){
    $('#fileform').addClass('no-display');
    $('#singleNumForm').addClass('no-display');
    $('#numlist').addClass('no-display');
    $('#check').addClass('no-display');
    $('.results').html('');
}

function checkSingleNum(n){
    $('#number').val("");
    var formData = new FormData();                  
    formData.append('number', n);
    $.ajax({
        url: 'checkNum',
        method: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false
    }).done(function(data){
        $('.results').html('Done');
        resetGui();
    });
}

function getCSV(){
    $.ajax({
        url: 'downloadCSV',
        async: true,
        headers: {
            Accept: 'application/octet-stream',
        }
    }).done(function(res){
        const a = document.createElement('a');
        a.style = 'display: none';
        document.body.appendChild(a);
        const blob = new Blob([res], {type: 'octet/stream'});
        const url = URL.createObjectURL(blob);
        a.href = url;
        a.download = 'numbers.csv';
        a.click();
        URL.revokeObjectURL(url);
        resetGui();
    });
}

function listNumbers(){
    $.ajax({
        url: 'listNum',
        async: true,
        dataType: "json"
    }).done(function(data){
    	$('#numlist').html('<div class="tb-body"><div>Id</div><div>Number</div><div>Original Number</div><div>Edit</div><div>Modified</div><div>Incorrect</div></div>');
    	data.forEach(element => {
    		$('<div class="tb-body"><div>'+element.id+'</div><div>'+element.number+'</div><div>'+element.original+'</div><div>'+element.edit+'</div><div>'+element.modified+'</div><div>'+element.incorrect+'</div></div>').appendTo("#numlist");
    	});
        resetGui();
    });
}

function uploadFile(f){
    var formData = new FormData();                  
    formData.append('up-file-input[]', f, f.name);
    $.ajax({
        url: 'checkCSV',
        method: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false
    }).done(function(data){
        console.log(data);
        resetGui();
    });
    
}

function resetGui(){
    $('#check').prop("disabled", false);
    $('#getCSV').prop("disabled", false);
    $('#getList').prop("disabled", false);
    $('#spinner').addClass('no-display');
}
