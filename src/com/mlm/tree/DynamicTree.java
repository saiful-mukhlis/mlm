package com.mlm.tree;

import com.global.App;
import org.jdesktop.swingx.JXTree;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;


public class DynamicTree {
	protected JPanel panel;
	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JXTree tree;
	
	
	
	public DynamicTree(String root) {
		rootNode = new DefaultMutableTreeNode(root);
		build();
	}

	public void build(){
		
		
		
	    treeModel = new DefaultTreeModel(rootNode);
	    tree = new JXTree(treeModel)
	    {
	    	  @Override public void paintComponent(Graphics g) {
	    		    g.setColor(getBackground());
	    		    g.fillRect(0,0,getWidth(),getHeight());
	    		    if(getSelectionCount()>0) {
	    		      for(int i: getSelectionRows()) {
	    		        Rectangle r = getRowBounds(i);
	    		        g.setColor(App.selected);
	    		        g.fillRect(0, r.y, getWidth(), r.height);
	    		      }
	    		    }
	    		    super.paintComponent(g);
//	    		    if(getLeadSelectionPath()!=null) {
//	    		      Rectangle r = getRowBounds(getRowForPath(getLeadSelectionPath()));
//	    		      g.setColor(App.selected);
//	    		      g.drawRect(0, r.y, getWidth()-1, r.height-1);
//	    		    }
	    		  }
	    		};
	    //tree.setRootVisible(false);
//	    tree.setUI(new javax.swing.plaf.basic.BasicTreeUI() {
//	    	  @Override public Rectangle getPathBounds(JTree tree, TreePath path) {
//	    	    if(tree != null && treeState != null) {
//	    	      return getPathBounds(path, tree.getInsets(), new Rectangle());
//	    	    }
//	    	    return null;
//	    	  }
//	    	  private Rectangle getPathBounds(TreePath path, Insets insets, Rectangle bounds) {
//	    	    bounds = treeState.getBounds(path, bounds);
//	    	    if(bounds != null) {
//	    	      bounds.width = tree.getWidth();
//	    	      bounds.y += insets.top;
//	    	    }
//	    	    return bounds;
//	    	  }
//	    	});
	    tree.setOpaque(false);
//	    DefaultTreeCellRenderer renderer2 = new DefaultTreeCellRenderer();
//	    renderer2.setOpenIcon(openIcon);
//	    renderer2.setClosedIcon(closedIcon);
//	    renderer2.setLeafIcon(leafIcon);
//	    tree.setCellRenderer(renderer2);
	    tree.setEditable(true);
	    tree.getSelectionModel().setSelectionMode(
	        TreeSelectionModel.SINGLE_TREE_SELECTION);
	    tree.setShowsRootHandles(true);
	    


	    JScrollPane scrollPane = new JScrollPane(tree);
	    scrollPane.setBorder(null);
	    panel=new JPanel(new BorderLayout());
	    panel.add(scrollPane, BorderLayout.CENTER);
	}
	
	 public void clear() {
		    rootNode.removeAllChildren();
		    treeModel.reload();
		  }

		  /** Remove the currently selected node. */
		  public void removeCurrentNode() {
		    TreePath currentSelection = tree.getSelectionPath();
		    if (currentSelection != null) {
		      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
		          .getLastPathComponent());
		      MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
		      if (parent != null) {
		        treeModel.removeNodeFromParent(currentNode);
		        return;
		      }
		    }

		  }

		  /** Add child to the currently selected node. */
		  public DefaultMutableTreeNode addObject(Object child) {
		    DefaultMutableTreeNode parentNode = null;
		    TreePath parentPath = tree.getSelectionPath();

		    if (parentPath == null) {
		      parentNode = rootNode;
		    } else {
		      parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		    }

		    return addObject(parentNode, child, true);
		  }

		  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child) {
		    return addObject(parent, child, false);
		  }

		  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child, boolean shouldBeVisible) {
		    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		    if (parent == null) {
		      parent = rootNode;
		    }

		    // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		    

		    // Make sure the user can see the lovely new node.
		    if (shouldBeVisible) {
		      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		    }
		    return childNode;
		  }

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(DefaultMutableTreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JXTree tree) {
		this.tree = tree;
	}
	
}
