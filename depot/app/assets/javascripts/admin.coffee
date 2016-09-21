# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

@OrderPoller =
  start: ->
    setInterval @fetchOrderCount, 2000

  fetchOrderCount: ->
    $.get '/orders/count'

  setOrdersCount: (text) ->
    $('#orders_count_text').html(text)


jQuery ->
  OrderPoller.start()

