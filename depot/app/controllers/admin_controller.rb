class AdminController < ApplicationController

  layout "admin"

  def index
    @orders_count = Order.count
  end
end
