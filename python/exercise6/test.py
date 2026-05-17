import pytest

# Identify Investment Return
#
# Given a purchased stock/cryptocurrency price changes, determine if the trend is a "gain/up", "loss/down", or "no gain/stable".
def identify_return(start_price, end_price):
    if end_price > start_price:
        return "Gain"
    elif end_price < start_price:
        return "Loss"
    else:
        return "No Gain"

def test_gain_return():
    assert identify_return(225, 261) == "Gain"

def test_loss_return():
    assert identify_return(456, 434) == "Loss"

def test_no_gain_return():
    assert identify_return(343, 343) == "No Gain"