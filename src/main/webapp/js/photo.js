$(document).ready(function () {
  $('select').change(function () {
    $(this).closest("form").submit();
  });
});