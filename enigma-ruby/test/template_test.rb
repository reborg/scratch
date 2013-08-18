require_relative 'test_helper'

describe "Target" do

  before do
    
  end

  it 'does something useful' do
    "it".must_equal "it"
  end

  after do
    
  end

end

#must_be
#must_be_close_to
#must_be_empty
#must_be_instance_of
#must_be_kind_of
#must_be_nil
#must_be_same_as
#must_be_silent
#must_be_within_delta
#must_be_within_epsilon
#must_equal
#must_include
#must_match
#must_output
#must_raise
#must_respond_to
#must_send
#must_throw
#
#  it 'demonstrate the mockist approach' do
#    mock = Mock.new
#    target = Target.new(mock)
#    mock.expect(:some, true, [String])
#    target.callme
#    mock.verify.must_equal true
#  end

