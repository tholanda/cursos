require 'test_helper'

class StoreControllerTest < ActionController::TestCase
  test "should get index" do
    get :index
    assert_response :success
    assert_select 'h1', 'catalogo de produtos'
    assert_select 'div', 3
  end

end
