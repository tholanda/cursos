class OrdersController < ApplicationController
  include CurrentCart
  before_action :set_cart
  skip_before_action :authorize


  def new
    if @cart.line_items.empty?
      redirect_to store_url, notice: "O carrinho está vazio"
      return
    end

    @order = Order.new
  end

  def count
    @orders_count = Order.count
  end

  def create
    # order_params = {
    #     name: params[:name],
    #     address: params[:address],
    #     email: params[:email],
    #     pay_type: params[:pay_type]
    # }
    @order = Order.new(order_params)
    @order.add_line_items_from_cart(@cart)

    respond_to do |format|
      if @order.save
        Cart.destroy(session[:cart_id])
        session[:cart_id] = nil
        format.html{redirect_to store_url, notice: "Obrigado!"}
      else
        format.html{render :new}
      end
    end
  end

  private
  def order_params
    params.require(:order).permit(:name, :address, :email, :pay_type)
  end

end