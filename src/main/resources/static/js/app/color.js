var isWins = '{{isWins}}';

    var table = document.getElementById('a');

    if (table && (isWins === '승리' || isWins === '패배')) {
    if (isWins === '승리') {
    table.classList.remove('alert-danger');
    table.classList.add('alert-primary');
    } else if (isWins === '패배') {
    table.classList.remove('alert-primary');
    table.classList.add('alert-danger');
    }
    }