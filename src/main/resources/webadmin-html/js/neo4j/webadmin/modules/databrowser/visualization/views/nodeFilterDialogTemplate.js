(function(define){
define(function(){return function(vars){
with(vars||{}) {
return "<h1>Choose nodes</h1><div class=\"filter\"></div><ul class=\"button-bar\"><li><button class=\"complete button\">Select</button></li><li><button class=\"selectAll button\">Select all</button></li><li><button class=\"cancel button bad-button\">Select none</button></li></ul>"; 
}};
});})(typeof define=="function"?
define:
function(factory){module.exports=factory.apply(this, deps.map(require));});
